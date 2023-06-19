#!/bin/sh
NAME=$1
DATA_DIR="./data"
EXE=./.stack-work/dist/x86_64-linux-tinfo6/Cabal-3.0.1.0/build/haskell-quoridor/haskell-quoridor
if [[ ! -d DATA_DIR ]]; then
   mkdir -p data
fi
stack build

# run minimax vs minimax
printf "Running Minimax vs Minimax as benchmark..."
time $EXE < scripts/minimax_duel.txt > "$DATA_DIR/$NAME""_default_results.txt"
printf "DONE\n"

# run reed vs minimax
printf "Running Reed vs Minimax as benchmark..."
time $EXE < scripts/reed_test.txt > "$DATA_DIR/$NAME""_reed_results.txt"
printf "DONE\n"
