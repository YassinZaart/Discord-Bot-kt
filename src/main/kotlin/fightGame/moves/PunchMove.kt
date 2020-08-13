package fightGame.moves
import embeds.EmbedCreator
import fightGame.FightManager
import discord4j.core.`object`.entity.channel.MessageChannel

class PunchMove: Move() {
    override var name = "Punch"
    override var description = "Attacks your opponent"

    override fun executeMove(fightManager : FightManager, fighter1Turn : Boolean, channel: MessageChannel) {
        val embedCreator = EmbedCreator(channel)
        var executor = fightManager.fighter1
        var standby = fightManager.fighter2
        if(!fighter1Turn) return


        val rand = (Math.random() * 101).toInt()
        if (executor.accuracy > rand) {
            embedCreator.createImageTitle(executor.name + " missed the punch, what a shame", Constants.MISSED_PUNCH_URL)
            return
        }
        val ceiling = executor.attack * standby.defense
        val range = (ceiling - ceiling / 2 + 1)
        val damage = (Math.random() * range).toInt() + (ceiling / 2)
        standby.hp -= damage
        if (standby.hp < 0) standby.hp = 0
        embedCreator.createImageTitle(executor.name + " deals "
                + damage + " to " + standby.name + "leaving them with " + standby.hp + " hp!", Constants.PUNCH_URL)


    }


}