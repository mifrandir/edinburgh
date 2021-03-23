(define (domain travelling)
    (:requirements :adl)

    (:types
        villain weapon agent - displacable
        chamber
    )

    (:constants
        ;; You should not need to add any additional constants
        Agent - agent
    )

    (:predicates
        (connected ?x - chamber ?y - chamber)
        (isAt ?x - chamber ?y - displacable)
        (defeated ?v - villain)
        (holding ?x - weapon)
    )

    (:functions
    )

    (:action move
        :parameters (?x - chamber ?y - chamber)
        :precondition (and (connected ?x ?y) (isAt ?x Agent))
        :effect (and (not (isAt ?x Agent)) (isAt ?y Agent))
    )

    (:action pickUp
        :parameters (?s - weapon)
        :precondition (and
            (not (exists
                    (?s - weapon)
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
        :effect (holding ?s)
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
                (holding ?s)
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
)