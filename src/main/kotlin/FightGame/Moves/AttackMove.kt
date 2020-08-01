package FightGame.Moves
import FightGame.Fighter

class PunchMove(executor : Fighter, standby : Fighter) : Move(executor, standby) {
    override var name = "Punch"
    override var description = "Attacks your opponent"

    override fun execute() {

    }
}