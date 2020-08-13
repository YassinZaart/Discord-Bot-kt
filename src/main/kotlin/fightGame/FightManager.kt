package fightGame

import fightGame.moves.Move
import fightGame.moves.PunchMove

class FightManager {

    var fighter1 = Fighter("Fighter1", 100, 20, 1, 0)
    var fighter2 = Fighter("Fighter2", 100, 20, 1, 0)

    var fighter1Turn = true
    var fighter2Turn = false

    var moveSet1 = Array<Move>(4) { i -> PunchMove() }
    var moveSet2 = Array<Move>(4) { i -> PunchMove() }

    fun isFinished() : Boolean{
        return fighter1.hp == 0 || fighter2.hp ==0
    }

}


