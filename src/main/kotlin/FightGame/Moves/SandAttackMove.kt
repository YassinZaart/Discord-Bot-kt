package FightGame.Moves

import FightGame.FightManager
import discord4j.core.`object`.entity.channel.MessageChannel
import Embeds.EmbedCreator

class SandAttackMove : Move() {
    override var name = "Sand Attack"

    override var description = "Lowers your opponents accuracy by 15%"

    override fun executeMove(fightManager: FightManager, channel: MessageChannel) {
        var standby = fightManager.standby
        if(standby.accuracy > 75){
            channel.createMessage("Your opponents accuracy cant be lowered anymore, choose another move").subscribe()
            return
        }
        standby.accuracy += 15
        var embedCreator = EmbedCreator(channel)
        embedCreator.createImageTitle("${standby.name}'s is lowered by 15%", Constants.SAND_ATTACK_URL)
        fightManager.switchTurns()

    }
}