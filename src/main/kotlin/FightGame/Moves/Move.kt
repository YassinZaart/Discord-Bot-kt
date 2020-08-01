package FightGame.Moves

import FightGame.Fighter

abstract class Move(var excutor : Fighter, var standby : Fighter) : Executable {
    abstract var name : String
    abstract var description : String

    abstract override fun execute()

}