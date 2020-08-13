package fightGame.moves

import fightGame.FightManager
import discord4j.core.`object`.entity.channel.MessageChannel
import embeds.EmbedCreator

class SharpenMove() : Move() {
    override var name = "Sharpen"
    override var description = "Increases your Attack stat"

    override fun executeMove(fightManager: FightManager, channel: MessageChannel) {
       var executor = fightManager.getExecutor()
       executor.attack = (executor.attack * 1.5).toInt()
       fightManager.switchTurns()
       val embedCreator = EmbedCreator(channel)
       embedCreator.createImageTitle("${executor.name}'s attack stat increased!", Constants.SHARPEN_URL)

    }
}