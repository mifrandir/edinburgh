memcpy:         # $a0 = src, $a1 = tg, $a2 = n
    beqz $a2,memcpy_end
    lw   $t0,0($a0)
    sw   $t0,0($a1)
    addi $a0,$a0,4
    addi $a1,$a1,4
    addi $a2,-1
    j memcpy
memcpy_end:
    jr $ra