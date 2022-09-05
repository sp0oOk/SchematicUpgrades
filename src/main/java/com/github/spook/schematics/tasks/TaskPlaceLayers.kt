package com.github.spook.schematics.tasks

import com.github.spook.schematics.SchematicsPlugin
import com.github.spook.schematics.`object`.SchematicProvider
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TaskPlaceLayers(private val schematic: SchematicProvider, private val layer: Int, private val start: Location, private val player: Player) : BukkitRunnable() {

    override fun run() {
        if(layer >= schematic.height) {
            player.sendMessage(SchematicsPlugin.instance!!.format("&8&l<&d&lDebug&8&l> &7Finished Building!"))
            return
        }

        for (z in 0 until schematic.length) {
            for (x in 0 until schematic.width) {
                val loc: Location = start.clone().add(x.toDouble(), layer.toDouble(), z.toDouble())
                val idx = x + (layer * schematic.length + z) * schematic.width
                try {
                    loc.block.setTypeIdAndData(schematic.blockIds[idx].toInt(), schematic.blockData[idx], false)
                } catch (_: java.lang.Exception) { }
            }
        }

        player.sendMessage(SchematicsPlugin.instance!!.format("&8&l<&d&lDebug&8&l> &7layerNow: &d$layer &7| sizeBlocks &d${schematic.blockCount} &7| heightTotal &d${schematic.height}&7!"))

        TaskPlaceLayers(schematic, layer + 1, start, player).runTaskLater(SchematicsPlugin.instance, 20L)
    }

}