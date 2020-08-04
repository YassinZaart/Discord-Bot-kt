package FightGame

import FightGame.Moves.Move
import FightGame.Moves.PunchMove

class FightManager {

    var fighter1 = Fighter("Fighter1", 100, 20, 1, 0, true)
    var fighter2 = Fighter("Fighter2", 100, 20, 1, 0, false)

    var moveSet1 = Array<Move>(4) { i -> PunchMove() }
    var moveSet2 = Array<Move>(4) { i -> PunchMove() }

    fun isFinished() : Boolean{
        return fighter1.hp == 0 || fighter2.hp ==0
    }

}


