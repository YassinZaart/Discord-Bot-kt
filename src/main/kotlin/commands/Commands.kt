package commands

import discord4j.common.util.Snowflake

interface Commands{

    fun mute(muteRoleID : Snowflake, userID: Snowflake)

    fun kick(userID : Snowflake)

    fun ban(userID: Snowflake)

    fun kiss(userID: Snowflake)

}