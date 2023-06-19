(define (problem travelling_31)
    (:domain travelling)
    (:objects
        Sword - weapon
        Whetstone - whetstone
        Key - key
        A B C D E F L - chamber
        Minobot - villain
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
        (isAt E Agent)
        (isAt F Key)
        (isAt E Whetstone)
        (isAt L Minobot)
        (isAt D Sword)
    )

    (:goal
        (defeated Minobot)
    )
)