package FightGame.Moves

import FightGame.FightManager
import discord4j.core.`object`.entity.channel.MessageChannel
import Embeds.EmbedCreator

class DefendMove : Move(){

    override var name = "Defend"
    override var description = "Raises your defense stats"

    override fun executeMove(fightManager: FightManager, channel: MessageChannel) {
        val executor = fightManager.executor
        if(executor.defense <= 0){ channel.createMessage("Your defense cant be increased anymore," +
                " choose another move").subscribe()
            return
        }
        executor.defense -= 0.3
        fightManager.switchTurns()
        val embedCreator = EmbedCreator(channel)
        embedCreator.createImageTitle("${executor.name}'s defense stat increased!", Constants.DEFEND_URL)

    }
}