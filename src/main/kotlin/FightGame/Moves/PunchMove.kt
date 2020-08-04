package FightGame.Moves
import Embeds.EmbedCreator
import FightGame.Fighter
import discord4j.core.`object`.entity.channel.MessageChannel

class PunchMove: Move() {
    override var name = "Punch"
    override var description = "Attacks your opponent"

    override fun executeMove(executor : Fighter, standby : Fighter, channel: MessageChannel) {
        val embedCreator = EmbedCreator(channel)

        executor.turn = false
        standby.turn = true

        val rand = (Math.random() * 101).toInt()
        if (executor.accuracy > rand) {
            embedCreator.missedPunch(executor.name + " missed the punch, what a shame")
            return
        }
        val ceiling = executor.attack * standby.defense
        val range = (ceiling - ceiling / 2 + 1)
        val damage = (Math.random() * range).toInt() + (ceiling / 2)
        standby.hp -= damage
        if (standby.hp < 0) standby.hp = 0
        embedCreator.createPunchEmbed(executor.name + " deals "
                + damage + " to " + standby.name + "leaving them with " + standby.hp + " hp!")


    }


}