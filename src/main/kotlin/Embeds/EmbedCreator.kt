package Embeds

import discord4j.core.`object`.Embed
import discord4j.core.`object`.entity.channel.MessageChannel
import discord4j.rest.util.Color

class EmbedCreator(var channel: MessageChannel) {

    fun createImageTitle(url : String, title : String){
        channel.createEmbed { embedCreateSpec -> embedCreateSpec.setImage(url).setTitle(title) }.subscribe()
    }

    fun createImageTitleFD(url : String, title : String, urlFooter : String, footer: String, description : String, titleURL : String){
        channel.createEmbed { embedCreateSpec -> embedCreateSpec
            .setImage(url).setTitle(title)
            .setFooter(footer,urlFooter).setDescription(description)
            .setUrl(titleURL).setColor(Color.PINK)}.subscribe()
    }


}