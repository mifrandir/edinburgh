(define (domain travelling)
    (:requirements :adl)

    (:types
        villain item agent whetstone - displacable
        weapon key - item
        chamber
    )

    (:constants
        Agent - agent
    )

    (:predicates
        (connected ?x - chamber ?y - chamber)
        (locked ?x - chamber ?y - chamber)
        (isAt ?x - chamber ?y - displacable)
        (defeated ?v - villain)
        (holding ?x - item)
        (isSharp ?s - weapon)
    )

    (:functions
    )

    (:action move
        :parameters (?x - chamber ?y - chamber)
        :precondition (and (connected ?x ?y) (isAt ?x Agent))
        :effect (and (not (isAt ?x Agent)) (isAt ?y Agent))
    )

    (:action pickUp
        :parameters (?s - item)
        :precondition (and
            (not (exists
                    (?s - item)
                    (holding ?s)
                )
            )
            (exists
                (?c - chamber)
                (and
                    (isAt ?c Agent)
                    (isAt ?c ?s)
                )
            )
        )
        :effect (and (holding ?s) (forall
                (?x - chamber)
                (not (isAt ?x ?s))))
    )

    (:action dropAt
        :parameters (?x - chamber ?s - item)
        :precondition (and
            (holding ?s)
            (isAt ?x Agent)
        )
        :effect (and
            (not (holding ?s))
            (isAt ?x ?s)
        )
    )

    (:action slay
        :parameters (?v - villain)
        :precondition (and
            (exists
                (?c - chamber)
                (and (isAt ?c ?v) (isAt ?c Agent))
            )
            (exists
                (?s - weapon)
                (and (holding ?s) (isSharp ?s))
            )
        )
        :effect (and
            (defeated ?v)
            (forall
                (?c - chamber)
                (not (isAt ?c ?v))
            )
        )
    )

    (:action unlock
        :parameters (?x - chamber ?y - chamber)
        :precondition (and
            (isAt ?x Agent)
            (locked ?x ?y)
            (exists
                (?k - key)
                (holding ?k)
            )
        )
        :effect (and
            (connected ?x ?y)
            (connected ?y ?x)
        )
    )
    (:action sharpen
        :parameters (?s - weapon)
        :precondition (and
            (holding ?s)
            (not (isSharp ?s))
            (exists
                (?x - chamber)
                (and
                    (isAt ?x Agent)
                    (exists
                        (?w - whetstone)
                        (isAt ?x ?w))
                )
            )
        )
        :effect (isSharp ?s)
    )

)