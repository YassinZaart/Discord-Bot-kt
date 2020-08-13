package fightGame

import fightGame.moves.*

class FightManager {

    var fighter1 = Fighter("Fighter1", 100, 20, 1.0, 0)
    var fighter2 = Fighter("Fighter2", 100, 20, 1.0, 0)

    var fighter1Turn = true
    var fighter2Turn = false

    var moveSet1 = Array<Move>(4) { initMoveSet(it) }
    var moveSet2 = Array<Move>(4) { initMoveSet(it) }


    fun isFinished() : Boolean{
        return fighter1.hp == 0 || fighter2.hp ==0
    }

    fun switchTurns(){
        fighter1Turn = !fighter1Turn
        fighter2Turn = !fighter2Turn
    }

    fun getExecutor() : Fighter{
        return if(fighter1Turn) fighter1
        else fighter2
    }

    fun getStandby() : Fighter{
        return if(fighter1Turn) fighter2
        else fighter1
    }

    fun initMoveSet(index : Int) : Move {
        return when(index){
            0 -> PunchMove()
            1 -> SharpenMove()
            2 -> DefendMove()
            else -> SandAttackMove()
        }
    }

}


