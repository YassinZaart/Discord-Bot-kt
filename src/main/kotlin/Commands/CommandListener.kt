package Commands

import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.channel.MessageChannel
import reactor.core.publisher.Flux

class CommandListener(var messageFlux: Flux<Message>, var prefix: String) {

    fun listen(){
        messageFlux.doOnNext {message ->
           // if(isACommand(message.content, CommandType.KICK.commandName, prefix)) kick(message)
            if(isACommand(message.content, CommandType.KISS.commandName, prefix)) kiss(message)



        }.subscribe()
    }

    private fun kick(message: Message){
        val formatChecker = CommandFormat(message)
        when(formatChecker.simpleFormatCheck()){
            SimpleFormatState.NO_MENTIONS -> sendMessage(message, "Mention a User")
            SimpleFormatState.MORE_THAN_ONE_MENTION -> sendMessage(message, "Mention only one User")
            else -> {
                val userID = message.userMentionIds.iterator().next()
                val operation = CommandOperation(message)
                operation.kick(userID)
            }
        }
    }

    private fun kiss(message: Message){
        val formatChecker = CommandFormat(message)
        when(formatChecker.simpleFormatCheck()){
            SimpleFormatState.NO_MENTIONS -> sendMessage(message, "Choose who u wanna kiss :(")
            SimpleFormatState.MORE_THAN_ONE_MENTION -> sendMessage(message, "You can only kiss one person")
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