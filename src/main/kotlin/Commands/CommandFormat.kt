package Commands

import discord4j.core.`object`.entity.Message
import java.util.*

class CommandFormat(var message : Message) {

    fun simpleFormatCheck() : SimpleFormatState {
        var mentions = message.userMentionIds
        if(mentions.isEmpty()) return SimpleFormatState.NO_MENTIONS
        if(mentions.size > 1) return SimpleFormatState.MORE_THAN_ONE_MENTION
        return SimpleFormatState.NO_ERROR
    }

    fun  ReasonFormatCheck() : ReasonFormatState{
        val scanner = Scanner(message.content)
        scanner.next()
        scanner.next()
        if(scanner.hasNext()) return ReasonFormatState.NO_REASON
        var mentions = message.userMentionIds
        if(mentions.isEmpty()) return ReasonFormatState.NO_MENTION
        if(mentions.size > 1) return ReasonFormatState.MORE_THAN_ONE_MENTION
        return ReasonFormatState.NO_ERROR

    }
}