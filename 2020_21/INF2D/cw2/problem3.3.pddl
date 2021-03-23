(define (problem travelling_32)
    (:domain travelling)
    (:objects
        Sword - weapon
        Whetstone - whetstone
        Key - key
        A B C D E F L - chamber
        Minobot - villain
        Ball1 Ball2 - yarn
    )

    (:init
        (connected A B)
        (connected B A)
        (connected B C)
        (connected B E)
        (connected C B)
        (connected C D)
        (connected D C)
        (connected E B)
        (connected E F)
        (connected F E)
        (locked A L)
        (locked L A)
        (isAt E BoTheseus)
        (isAt E Autoadne)
        (isAt F Key)
        (isAt E Whetstone)
        (isAt L Minobot)
        (isAt D Sword)
        (isAt E Ball1)
        (= (numLeft Ball1) 3)
        (isAt C Ball2)
        (= (numLeft Ball2) 3)
        (marked E)
    )

    (:goal
        (defeated Minobot)
    )
)