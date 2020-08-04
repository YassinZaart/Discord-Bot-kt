package Commands

import Embeds.EmbedCreator
import discord4j.common.util.Snowflake
import discord4j.core.`object`.entity.Guild
import discord4j.core.`object`.entity.Member
import discord4j.core.`object`.entity.Message
import discord4j.core.`object`.entity.channel.MessageChannel
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.function.TupleUtils
import reactor.util.function.Tuple2

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
        Flux.zip(message.channel, message.guild, message.authorAsMember)
            .doOnNext { tuple3 ->
                tuple3.t2.getMemberById(userID).doOnNext { reciever ->
                    var channel = tuple3.t1
                    var kisser = tuple3.t3
                    val embedCreator = EmbedCreator(channel)
                    var title = kisser.displayName + " kisses " + reciever.displayName + ", Adorbs <3"

                    if (userID.asString() == "731729124535697408")
                        embedCreator.createImageTitleFD(
                            ConstantValues.KISS_URL.value, title, ConstantValues.KISS_FOOTER_URL.value,
                            ConstantValues.KISS_FOOTER.value, ConstantValues.KISS_DESCRIPTION.value,
                            ConstantValues.TRICCX_URL.value)
                    else
                        embedCreator.createImageTitle(ConstantValues.KISS_URL.value, title)


                }.subscribe()
            }.subscribe()
    }
}