import commands.CommandListener
import discord4j.core.DiscordClientBuilder
import discord4j.core.GatewayDiscordClient
import discord4j.core.`object`.presence.Activity
import discord4j.core.`object`.presence.Presence
import discord4j.core.event.domain.message.MessageCreateEvent

public class Bot(token : String, var prefix : String){

    private val client: GatewayDiscordClient = DiscordClientBuilder
        .create(token)
        .build()
        .login()
        .block()


    fun run(){
        client.updatePresence(Presence.online(Activity.watching(Constants.STATUS))).subscribe()

        var messageFlux = client.eventDispatcher.on(MessageCreateEvent::class.java).map { it.message }
            .filter{ message -> message.content.length >= prefix.length }
            .filter{ message -> message.content.substring(0,prefix.length) == prefix  }
        var commandListener = CommandListener(messageFlux, prefix, client)
       commandListener.listen()
       client.onDisconnect().block()

    }

}