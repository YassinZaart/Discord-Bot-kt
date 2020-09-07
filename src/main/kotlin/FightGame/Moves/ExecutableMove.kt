package FightGame.Moves

import FightGame.FightManager
import discord4j.core.`object`.entity.channel.MessageChannel

interface ExecutableMove {
    fun executeMove(fightManager : FightManager, channel: MessageChannel)
}