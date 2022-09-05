package com.github.spook.schematics

import com.github.spook.schematics.cmd.CmdBuild
import com.github.spook.schematics.exclusions.IgnoreCompanion
import com.massivecraft.massivecore.MassivePlugin
import org.bukkit.ChatColor

class SchematicsPlugin : MassivePlugin() {

    companion object {
        var instance: SchematicsPlugin? = null
            private set
    }

    init {
        instance = this
        versionSynchronized = false
    }


    override fun onEnableInner() {

        val builder = super.getGsonBuilder()
        builder.setExclusionStrategies(IgnoreCompanion())
        super.setGson(builder.create())

        activate(
            CmdBuild::class.java
        )
    }

    override fun onDisable() {
        instance = null
        super.onDisable()
    }

    fun format(msg: String): String {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }


}