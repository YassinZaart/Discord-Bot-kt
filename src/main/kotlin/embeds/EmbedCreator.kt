package embeds

import fightGame.moves.Move
import discord4j.core.`object`.entity.channel.MessageChannel
import discord4j.rest.util.Color

class EmbedCreator(var channel: MessageChannel) {

    fun createImageTitle(url: String, title: String) {
        channel.createEmbed { embedCreateSpec -> embedCreateSpec.setImage(url).setTitle(title) }.subscribe()
    }

    fun createImageTitleFD(
        url: String,
        title: String,
        urlFooter: String,
        footer: String,
        description: String,
        titleURL: String
    ) {
        channel.createEmbed { embedCreateSpec ->
            embedCreateSpec
                .setImage(url).setTitle(title)
                .setFooter(footer, urlFooter).setDescription(description)
                .setUrl(titleURL).setColor(Color.PINK)
        }.subscribe()
    }

    fun createTurnEmbed(moves: Array<Move>, name: String) {
        if(moves.size != 4) return
        channel.createEmbed { spec ->
            spec.setColor(Color.PINK)
                .setTitle("Its $name's turn, what do you want to do")
                .addField(moves[0].name, moves[0].description, true)
                .addField(moves[1].name, moves[1].description, true)
                .addField(Constants.BLANK_CHARACTER, Constants.BLANK_CHARACTER, false)
                .addField(moves[2].name, moves[2].description, true)
                .addField(moves[3].name, moves[3].description, true)
        }.subscribe()
    }

}