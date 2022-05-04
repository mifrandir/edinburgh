(define (problem travelling-21)
    (:domain travelling)
    (:objects
        Sword - weapon
        A B C D E F L - chamber
        Minobot - villain
    )

    (:init
        (connected A B)
        (connected A L)
        (connected B A)
        (connected B C)
        (connected B E)
        (connected C B)
        (connected C D)
        (connected D C)
        (connected E B)
        (connected E F)
        (connected F E)
        (connected L A)
        (isAt E Agent)
        (isAt L Minobot)
        (isAt D Sword)
    )

    (:goal
        (defeated Minobot)
    )
)