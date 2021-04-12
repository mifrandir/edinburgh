#!/usr/bin/python
import os, sys, subprocess

OUT_DIR = "./out"
TEX_CMD = [
    "latexmk", "-lualatex", "-synctex=1", "-file-line-error", "-shell-escape",
    "--interaction=errorstopmode"
]


def compile_with_recipe(tex_file):
    tex_dir = os.path.dirname(tex_file)
    tex_file_name = os.path.basename(tex_file)
    cmd = TEX_CMD + [f"-outdir=.", tex_file_name]
    return subprocess.run(cmd,
                          stdout=subprocess.PIPE,
                          stderr=subprocess.PIPE,
                          cwd=tex_dir)


def is_target(tex_path):
    _, e = os.path.splitext(tex_path)
    if e != '.tex':
        return False
    f = os.path.basename(tex_path)
    if f == '__latexindent_temp.tex':
        return False
    return True


def compile_pdf(tex_path):
    if not is_target(tex_path):
        print(
            f"WARN: The given file is not a suitable source file. Skipping. ({tex_path})",
            flush=True)
        return
    print(f"Compiling {tex_path}...", end='', flush=True)
    tex_path_no_ext, _ = os.path.splitext(tex_path)
    p = compile_with_recipe(tex_path)
    if p.returncode:
        print("FAIL (Compilation)", flush=True)
        return
    target_file = tex_path_no_ext + ".pdf"
    tex_dir = os.path.dirname(tex_path)
    tex_parent_dirs = tex_dir.split('/')
    if tex_parent_dirs[0] == '.':
        tex_parent_dirs = tex_parent_dirs[1:]
    out_name = '-'.join(tex_parent_dirs) + '.pdf'
    p = subprocess.run(
        ['cp',
         os.path.join(target_file),
         os.path.join(OUT_DIR, out_name)],
        stdout=subprocess.PIPE)
    if p.returncode:
        print("FAIL (Copying)", flush=True)
    else:
        print("DONE", flush=True)


def find_all_tex():
    targets = []
    for r, _, f in os.walk(os.curdir):
        targets.extend(map(lambda x, r=r: os.path.join(r, x), f))
    return list(filter(is_target, targets))


def default_build():
    ts = find_all_tex()
    for t in ts:
        compile_pdf(t)


def prep_dirs():
    if not os.path.isdir(OUT_DIR):
        os.mkdir(OUT_DIR)


def clean():
    if os.path.isdir(OUT_DIR):
        subprocess.call(['rm', '-r', OUT_DIR])


if __name__ == "__main__":
    if len(sys.argv) < 2 or sys.argv[1] == 'default':
        prep_dirs()
        default_build()
    elif sys.argv[1] == 'clean':
        clean()
        prep_dirs()
        default_build()
    else:
        prep_dirs()
        compile_pdf(sys.argv[1])
