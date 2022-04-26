#!/usr/bin/python3
import os
import sys
import subprocess
import multiprocessing
import time
from typing import List, Optional

OUT_DIR = "./out"
TEX_CMD = [
    "latexmk",
    "-lualatex",
    "-synctex=1",
    "-file-line-error",
    "-shell-escape",
    "--interaction=nonstopmode",
]
FORMAT_CMD = ["latexindent", "-w"]


def execute(cmd: List[str], cwd: str, timeout=5) -> subprocess.CompletedProcess[bytes]:
    p = subprocess.run(
        cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, cwd=cwd, timeout=5
    )
    if p.returncode:
        out = [
            "In directory",
            ">" + " ".join(cmd),
            "--- BEGIN STDERR ---",
            p.stderr.decode("utf-8"),
            "--- END STDERR ---",
        ]
        print("\n".join(out), flush=True)
    return p


def clear_format_backup(tex_file: str) -> Optional[subprocess.CompletedProcess[bytes]]:
    tex_dir = os.path.dirname(tex_file)
    tex_file_name = os.path.basename(tex_file)
    backup_file_name = os.path.splitext(tex_file_name)[0] + ".bak0"
    if not os.path.isfile(os.path.join(tex_dir, backup_file_name)):
        return None
    cmd = ["rm", backup_file_name]
    return execute(cmd, tex_dir)


def format(tex_file: str) -> subprocess.CompletedProcess[bytes]:
    tex_dir = os.path.dirname(tex_file)
    tex_file_name = os.path.basename(tex_file)
    cmd = FORMAT_CMD + [tex_file_name]
    return execute(cmd, tex_dir)


def compile_with_recipe(tex_file: str) -> subprocess.CompletedProcess[bytes]:
    tex_dir = os.path.dirname(tex_file)
    tex_file_name = os.path.basename(tex_file)
    cmd = TEX_CMD + [f"-outdir=.", tex_file_name]
    return execute(cmd, tex_dir, timeout=100)


def is_target(tex_path: str) -> bool:
    _, e = os.path.splitext(tex_path)
    if e != ".tex":
        return False
    f = os.path.basename(tex_path)
    if f == "__latexindent_temp.tex":
        return False
    return True


def compile_pdf(tex_path: str) -> int:
    if not is_target(tex_path):
        print(
            f"WARN: The given file is not a suitable source file. Skipping. ({tex_path})",
            flush=True,
        )
        return 0
    t = time.time()
    tex_path_no_ext, _ = os.path.splitext(tex_path)
    p = clear_format_backup(tex_path)
    if p and p.returncode:
        print(f"{tex_path} - FAIL (clearing format backup)", flush=True)
        return 0
    p = format(tex_path)
    if p and p.returncode:
        print(f"{tex_path} - FAIL (formatting)", flush=True)
        return 0
    p = compile_with_recipe(tex_path)
    if p and p.returncode:
        print(f"{tex_path} - FAIL (compilation)", flush=True)
        return 0
    target_file = tex_path_no_ext + ".pdf"
    tex_dir = os.path.dirname(tex_path)
    tex_parent_dirs = tex_dir.split("/")
    if tex_parent_dirs[0] == ".":
        tex_parent_dirs = tex_parent_dirs[1:]
    out_name = "-".join(tex_parent_dirs) + ".pdf"
    p = subprocess.run(
        ["cp", os.path.join(target_file), os.path.join(OUT_DIR, out_name)],
        stdout=subprocess.PIPE,
    )
    if p and p.returncode:
        print(f"{tex_path} - FAIL (copy)", flush=True)
        return 0
    else:
        print(f"{tex_path} - DONE in ({time.time() - t:.2f}s)", flush=True)
        return 1


def find_all_tex(path: str) -> List[str]:
    targets = []
    for r, _, f in os.walk(path):
        targets.extend(map(lambda x, r=r: os.path.join(r, x), f))
    return sorted(list(filter(is_target, targets)))


def build_default_dir() -> None:
    build_dir(os.curdir)


def build_dir(path: str, num_cores: int = 8) -> None:
    sources = find_all_tex(path)
    t = time.time()
    print(f"Found {len(sources)} source files, starting compilation.")
    with multiprocessing.Pool(num_cores) as pool:
        results = pool.map(compile_pdf, sources)
    num_compiled = sum(results)
    print(
        f"Compiled {num_compiled} of {len(sources)} files in {time.time() - t:.2f} seconds."
    )


def prep_dirs() -> None:
    if not os.path.isdir(OUT_DIR):
        os.mkdir(OUT_DIR)


def clean() -> None:
    if os.path.isdir(OUT_DIR):
        subprocess.call(["rm", "-r", OUT_DIR])


if __name__ == "__main__":
    if len(sys.argv) < 2 or sys.argv[1] == "default":
        prep_dirs()
        build_default_dir()
    elif sys.argv[1] == "clean":
        clean()
        prep_dirs()
        build_default_dir()
    else:
        for i, path in enumerate(sys.argv[1:]):
            prep_dirs()
            if os.path.isfile(path):
                compile_pdf(path)
            elif os.path.isdir(path):
                build_dir(path)
            else:
                print(f"ERROR: Unknown path in argument #{i} ({path})")
