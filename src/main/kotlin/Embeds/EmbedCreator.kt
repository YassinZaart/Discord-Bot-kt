package Embeds

import FightGame.Moves.Move
import discord4j.core.`object`.Embed
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

    fun missedPunch(description: String) {
        val url = "https://media.tenor.com/images/3ee76c0871624822103216e82f0f89ca/tenor.gif"
        channel.createEmbed { embedCreateSpec ->
            embedCreateSpec.setColor(Color.RED)
            embedCreateSpec.setImage(url).setTitle(description)
        }.subscribe()

    }

    fun createPunchEmbed(description: String) {
        val url = "https://media.tenor.com/images/4391e2653987eb148b9e9cbbfe301e31/tenor.gif"
        channel.createEmbed { embedCreateSpec ->
            embedCreateSpec.setColor(Color.RED)
            embedCreateSpec.setImage(url).setTitle(description)
        }.subscribe()

    }

    fun createTurnEmbed(moves: Array<Move>, name: String) {
        channel.createEmbed { spec ->
            spec.setColor(Color.PINK)
                .setTitle("Its $name's turn, what do you want to do")
                .addField(moves[0].name, moves[0].description, true)
                .addField(moves[1].name, moves[1].description, true)
                .addField("\u200E\u200E", "\u200E\u200E", false)
                .addField(moves[2].name, moves[2].description, true)
                .addField(moves[3].name, moves[3].description, true)
        }.subscribe()
    }
    fun createCongratsEmbed(channel: MessageChannel, description: String) {
        val url = "https://media.tenor.com/images/a8066d69392f6e46651c44e5822342b6/tenor.gif"
        channel.createEmbed { embedCreateSpec ->
            embedCreateSpec.setColor(Color.RED)
            embedCreateSpec.setImage(url).setTitle(description)
        }.subscribe()
    }
}