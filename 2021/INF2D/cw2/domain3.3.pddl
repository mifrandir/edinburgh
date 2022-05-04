(define (domain travelling)
    (:requirements :adl)

    (:types
        villain item agent whetstone - displacable
        weapon key yarn - item
        chamber
    )

    (:constants
        Autoadne BoTheseus - agent
    )

    (:predicates
        (connected ?x - chamber ?y - chamber)
        (locked ?x - chamber ?y - chamber)
        (isAt ?x - chamber ?y - displacable)
        (defeated ?v - villain)
        (holding ?x - item ?a - agent)
        (isSharp ?s - weapon)
        (marked ?x - chamber)
    )

    (:functions
        (numLeft ?b - yarn)
    )

    (:action moveAndMark
        :parameters (?x - chamber ?y - chamber ?b - yarn)
        :precondition (and
            (connected ?x ?y)
            (isAt ?x Autoadne)
            (not (marked ?y))
            (holding ?b Autoadne)
            (> (numLeft ?b) 0)
        )
        :effect (and
            (not (isAt ?x Autoadne))
            (isAt ?y Autoadne)
            (marked ?y)
            (decrease (numLeft ?b) 1)
        )
    )

    (:action move
        :parameters (?x - chamber ?y - chamber ?a - agent)
        :precondition (and
            (connected ?x ?y)
            (isAt ?x ?a)
            (marked ?y)
        )
        :effect (and
            (not (isAt ?x ?a))
            (isAt ?y ?a)
        )
    )

    (:action pickUp
        :parameters (?s - item ?a - agent)
        :precondition (and
            (not (exists
                    (?s - item)
                    (holding ?s ?a)
                )
            )
            (exists
                (?c - chamber)
                (and
                    (isAt ?c ?a)
                    (isAt ?c ?s)
                )
            )
        )
        :effect (and
            (holding ?s ?a)
            (forall
                (?x - chamber)
                (not (isAt ?x ?s))
            )
        )
    )

    (:action dropAt
        :parameters (?x - chamber ?s - item ?a - agent)
        :precondition (and
            (holding ?s ?a)
            (isAt ?x ?a)
        )
        :effect (and
            (not (holding ?s ?a))
            (isAt ?x ?s)
        )
    )

    (:action slay
        :parameters (?v - villain)
        :precondition (and
            (exists
                (?c - chamber)
                (and (isAt ?c ?v) (isAt ?c BoTheseus))
            )
            (exists
                (?s - weapon)
                (and (holding ?s BoTheseus) (isSharp ?s))
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
        :parameters (?x - chamber ?y - chamber ?a - agent)
        :precondition (and
            (isAt ?x ?a)
            (locked ?x ?y)
            (exists
                (?k - key)
                (holding ?k ?a)
            )
        )
        :effect (and
            (connected ?x ?y)
            (connected ?y ?x)
        )
    )
    (:action sharpen
        :parameters (?s - weapon ?a - agent)
        :precondition (and
            (holding ?s ?a)
            (not (isSharp ?s))
            (exists
                (?x - chamber)
                (and
                    (isAt ?x ?a)
                    (exists
                        (?w - whetstone)
                        (isAt ?x ?w))
                )
            )
        )
        :effect (isSharp ?s)
    )

)