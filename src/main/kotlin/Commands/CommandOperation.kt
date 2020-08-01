package Commands

import Embeds.EmbedCreator
import discord4j.common.util.Snowflake
import discord4j.core.`object`.entity.Member
import discord4j.core.`object`.entity.Message

class CommandOperation(var message : Message) : ModCommandOperation {

    override fun mute(muteRoleID : Snowflake, userID : Snowflake) {
        message.guild.flatMap{ guild -> guild.getMemberById(userID)}
            .flatMap { member -> member.addRole(muteRoleID) }.subscribe()
    }

    override fun kick(userID: Snowflake){
        message.guild.flatMap { guild -> guild.kick(userID)}.subscribe()
    }

    override fun ban(userID: Snowflake){
        message.guild.flatMap { guild -> guild
            .ban(userID) { banQuerySpec -> banQuerySpec.setDeleteMessageDays(1)}
        }.subscribe()
    }

    override fun kiss(userID: Snowflake) {
        message.channel.doOnNext { channel ->
            message.guild.flatMap { guild -> guild.getMemberById(userID) }
                .doOnNext { reciever ->
                    message.authorAsMember.doOnNext {auther ->

                        val embedCreator = EmbedCreator(channel)
                        var title = auther.displayName + " kisses " + reciever.displayName + ", Adorbs <3"

                        if (userID.asString() == "731729124535697408")
                            embedCreator.createImageTitleFD(
                                ConstantValues.KISS_URL.value, title, ConstantValues.KISS_FOOTER_URL.value,
                                ConstantValues.KISS_FOOTER.value, ConstantValues.KISS_DESCRIPTION.value,
                            ConstantValues.TRICCX_URL.value)
                        else
                            embedCreator.createImageTitle(ConstantValues.KISS_URL.value, title)

                    }.subscribe()
                }.subscribe()
        }.subscribe()
    }
}