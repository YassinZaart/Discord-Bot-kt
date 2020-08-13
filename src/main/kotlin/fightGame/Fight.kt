package fightGame

import embeds.EmbedCreator
import fightGame.moves.Move
import discord4j.common.util.Snowflake
import discord4j.core.GatewayDiscordClient
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.channel.MessageChannel
import discord4j.core.event.domain.message.MessageCreateEvent

class Fight(var message: Message, var client: GatewayDiscordClient, var userID : Snowflake) {


    fun fight() {
        message.channel.doOnNext { channel  ->
            val fighterID = message.userData.id()
            val channelID = message.channelId
            val fightManager = FightManager()
            val embedCreator = EmbedCreator(channel)
            embedCreator.createTurnEmbed(fightManager.moveSet1, fightManager.fighter1.name)
            client.eventDispatcher.on(MessageCreateEvent::class.java).map { it.message }
                .filter { !fightManager.isFinished() }
                .filter { message -> message.channelId == channelID }
                .filter { message -> message.userData.id() == fighterID || message.userData.id() == userID.asString()}
                .doOnNext { message ->

                    if (fightManager.fighter1Turn && message.userData.id() == fighterID){
                            if (isMove(message.content, fightManager.moveSet1)){
                                executeMove(message.content, fightManager.moveSet1,fightManager,channel)
                                embedCreator.createTurnEmbed(fightManager.moveSet2, fightManager.fighter2.name)
                            }
                            else sendWrongMessage(channel, fightManager.moveSet1)
                        }
                    if (fightManager.fighter2Turn && message.userData.id() == userID.asString()){
                             if (isMove(message.content, fightManager.moveSet2)){
                            executeMove(message.content, fightManager.moveSet2, fightManager, channel)
                            embedCreator.createTurnEmbed(fightManager.moveSet1, fightManager.fighter1.name)
                            }
                            else sendWrongMessage(channel, fightManager.moveSet2)
                         }

                    if(fightManager.isFinished()){
                        if(fightManager.fighter1.hp == 0)
                            embedCreator.createImageTitle(fightManager.fighter2.name + " has won with "
                                    +fightManager.fighter2.hp + " hp left!", Constants.CONGRATS_URL)
                        else
                            embedCreator.createImageTitle(fightManager.fighter1.name + " has won with "
                                    +fightManager.fighter1.hp + " hp left!", Constants.CONGRATS_URL)
                    }

                    }.subscribe()
                }.subscribe()
    }

    private fun isMove(message: String, moves : Array<Move>) : Boolean{
        for (move in moves){
            if (move.name.equals(message, true)) return true
        }
        return false
    }

    private fun executeMove(message: String, moves: Array<Move>, fightManager: FightManager, channel: MessageChannel){
        for (move in moves){
            if (move.name.equals(message, true)){
                move.executeMove(fightManager,true, channel)
                return
            }
        }
    }

    private fun sendWrongMessage(channel: MessageChannel, moves: Array<Move>){
        if (moves.size != 4) return
        var move1 = moves[0].name
        var move2 = moves[1].name
        var move3 = moves[2].name
        var move4 = moves[3].name
        channel.createMessage("Choose one of the following moves $move1 $move2 $move3 $move4").subscribe()
    }
}