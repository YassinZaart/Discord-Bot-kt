package Commands

import discord4j.common.util.Snowflake
import discord4j.core.`object`.entity.Role

interface ModCommandOperation{

    fun mute(muteRoleID : Snowflake, userID: Snowflake)

    fun kick(userID : Snowflake)

    fun ban(userID: Snowflake)

    fun kiss(userID: Snowflake)

}