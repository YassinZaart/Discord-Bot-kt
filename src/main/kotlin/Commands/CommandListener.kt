package Commands

import FightGame.Fight
import discord4j.core.GatewayDiscordClient
import discord4j.core.`object`.entity.Message
import reactor.core.publisher.Flux

class CommandListener(var messageFlux: Flux<Message>, var prefix: String, var client : GatewayDiscordClient) {

    fun listen(){
        messageFlux.doOnNext {message ->
           // if(isACommand(message.content, CommandType.KICK.commandName, prefix)) kick(message)
            if(isACommand(message.content, CommandType.KISS.commandName, prefix)) kiss(message)
            else if(isACommand(message.content, CommandType.FIGHT.commandName, prefix)) fight(message)



        }.subscribe()
    }

    private fun kick(message: Message){
        val formatChecker = CommandFormatValidator(message)
        when(formatChecker.simpleFormatCheck()){
            SimpleFormatState.NO_MENTIONS -> sendMessage(message, Constants.KICK_NO_MENTIONS_MESSAGE)
            SimpleFormatState.MORE_THAN_ONE_MENTION -> sendMessage(message, Constants.KICK_MORE_THAN_ONE_MENTION_MESSAGE)
            else -> {
                val userID = message.userMentionIds.iterator().next()
                val operation = CommandOperation(message)
                operation.kick(userID)
            }
        }
    }

    private fun fight(message: Message){
        val formatChecker = CommandFormatValidator(message)
        when(formatChecker.simpleFormatCheck()){
            SimpleFormatState.NO_MENTIONS -> sendMessage(message, Constants.FIGHT_NO_MENTIONS_MESSAGE)
            SimpleFormatState.MORE_THAN_ONE_MENTION -> sendMessage(message, Constants.FIGHT_MORE_THAN_ONE_MENTION_MESSAGE)
            else -> {
                val userID = message.userMentionIds.iterator().next()
                val fight = Fight(message, client, userID)
                fight.fight()
            }
        }
    }

    private fun kiss(message: Message){
        val formatChecker = CommandFormatValidator(message)
        when(formatChecker.simpleFormatCheck()){
            SimpleFormatState.NO_MENTIONS -> sendMessage(message, Constants.KISS_NO_MENTIONS_MESSAGE)
            SimpleFormatState.MORE_THAN_ONE_MENTION -> sendMessage(message, Constants.KISS_MORE_THAN_ONE_MENTION_MESSAGE)
            else -> {
                val userID = message.userMentionIds.iterator().next()
                val operation = CommandOperation(message)
                operation.kiss(userID)
            }
        }
    }

    private fun isACommand(message: String, commandName : String, prefix : String) : Boolean{
        val commandPrefixLength = commandName.length + prefix.length
        return message.length >= commandPrefixLength
                && message.substring(prefix.length, commandPrefixLength) == commandName
    }

    private fun sendMessage(messageDestination : Message, message: String){
        messageDestination.channel
            .flatMap { messageChannel -> messageChannel.createMessage(message) }
            .subscribe()
    }
}