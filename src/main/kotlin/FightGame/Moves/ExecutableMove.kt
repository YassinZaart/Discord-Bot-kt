package FightGame.Moves

import FightGame.Fighter
import discord4j.core.`object`.entity.channel.MessageChannel

interface ExecutableMove {
    fun executeMove(executor : Fighter, standby : Fighter, channel: MessageChannel)
}