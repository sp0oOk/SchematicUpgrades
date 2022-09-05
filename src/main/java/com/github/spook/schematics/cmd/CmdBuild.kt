package com.github.spook.schematics.cmd

import com.github.spook.schematics.SchematicsPlugin
import com.github.spook.schematics.`object`.SchematicProvider
import com.github.spook.schematics.tasks.TaskPlaceLayers
import com.google.common.collect.Lists
import com.massivecraft.massivecore.command.MassiveCommand
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer
import com.massivecraft.massivecore.command.type.primitive.TypeString
import org.bukkit.entity.Player
import java.io.File

class CmdBuild : MassiveCommand() {

    init {
        addParameter(TypeString.get(), "schematic")
        addRequirements<MassiveCommand>(RequirementIsPlayer.get())
    }


    override fun getAliases(): MutableList<String> {
        return Lists.newArrayList("build")
    }

    override fun perform() {
        val file = File(SchematicsPlugin.instance?.dataFolder.toString() + "\\" + readArg())
        if(!file.exists()) {
            msg("File does not exist, try again")
            return
        }
        val schematic = SchematicProvider(file)
        val player: Player = sender as Player
        msg(SchematicsPlugin.instance!!.format("&8&l<&d&lDebug&8&l> &7Task started &asuccessfully&7!"))
        TaskPlaceLayers(schematic, 0, player.location, player).run()
    }


    companion object {
        private val i = CmdBuild()
        @JvmStatic
        fun get(): CmdBuild { return i }
    }

}