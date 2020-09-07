package FightGame.Moves

import FightGame.FightManager
import discord4j.core.`object`.entity.channel.MessageChannel

 abstract class Move() : ExecutableMove {
    abstract var name : String
    abstract var description : String

    abstract override fun executeMove(fightManager : FightManager, channel : MessageChannel)

}