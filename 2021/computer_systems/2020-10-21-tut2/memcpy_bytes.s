memcpy:
    srl $t0,$a2,2           # number of words to copy
memcpy_words:               # $a0 = src, $a1 = tg, $a2 = n
    beqz $t0,memcpy_bytes
    lw   $t1,0($a0)
    sw   $t1,0($a1)
    addi $a0,$a0,4
    addi $a1,$a1,4
    addi $t0,-1
    j memcpy_words
memcpy_bytes:
    beqz $t0,memcpy_end
    andi $t0,$a2,3
    lb   $t1,0($a0)
    sb   $t1,0($a1)
    addi $a0,$a0,1
    addi $a1,$a1,1
    addi $t0,-1
    j memcpy_bytes
memcpy_end:
    jr $ra