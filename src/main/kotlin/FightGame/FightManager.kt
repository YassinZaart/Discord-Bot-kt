package FightGame

import FightGame.Moves.*

class FightManager {

    val fighter1 = Fighter("Fighter1", 100, 20, 1.0, 0)
    val fighter2 = Fighter("Fighter2", 100, 20, 1.0, 0)

    val executor : Fighter
        get() {
            return if(fighter1Turn) fighter1
            else fighter2
        }

    val standby : Fighter
        get() {
            return if(fighter1Turn) fighter2
            else fighter1
        }

    var fighter1Turn = true
    var fighter2Turn = false

    var moveSet1 = Array<Move>(4) { getMove(it) }
    var moveSet2 = Array<Move>(4) { getMove(it) }


    fun isFinished() : Boolean{
        return fighter1.hp == 0 || fighter2.hp ==0
    }

    fun switchTurns(){
        fighter1Turn = !fighter1Turn
        fighter2Turn = !fighter2Turn
    }

    fun getMove(index : Int) : Move {
        return when(index){
            0 -> PunchMove()
            1 -> SharpenMove()
            2 -> DefendMove()
            else -> SandAttackMove()
        }
    }

}


