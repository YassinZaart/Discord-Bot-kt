package commands

import embeds.EmbedCreator
import discord4j.common.util.Snowflake
import discord4j.core.`object`.entity.Message
import reactor.core.publisher.Flux

class CommandOperation(var message : Message) : Commands {

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
        Flux.zip(message.channel, message.guild, message.authorAsMember)
            .doOnNext { tuple3 ->
                tuple3.t2.getMemberById(userID).doOnNext { reciever ->
                    val channel = tuple3.t1
                    val kisser = tuple3.t3
                    val embedCreator = EmbedCreator(channel)
                    val title = kisser.displayName + " kisses " + reciever.displayName + ", Adorbs <3"

                    if (userID.asString() == "731729124535697408")
                        embedCreator.createImageTitleFD(
                            Constants.KISS_URL, title, Constants.KISS_FOOTER_URL,
                            Constants.KISS_FOOTER, Constants.KISS_DESCRIPTION,
                            Constants.TRICCX_URL)
                    else
                        embedCreator.createImageTitle(Constants.KISS_URL, title)


                }.subscribe()
            }.subscribe()
    }
}