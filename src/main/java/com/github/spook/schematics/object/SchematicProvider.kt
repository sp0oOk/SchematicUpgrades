package com.github.spook.schematics.`object`

import net.minecraft.server.v1_8_R3.NBTCompressedStreamTools
import net.minecraft.server.v1_8_R3.NBTTagCompound
import net.minecraft.server.v1_8_R3.NBTTagList
import java.io.*

class SchematicProvider(file: File) {

    var blockCount: Int = 0
    var width: Short = 0
    var height: Short = 0
    var length: Short = 0
    lateinit var blockIds: ByteArray
    lateinit var blockData: ByteArray
    private lateinit var entities: NBTTagList
    private lateinit var tileEntities: NBTTagList

    init {
        loadSchematic(file)
    }

    companion object {
        private const val COMPOUND_ID: Int = 10
    }

    private fun updateFields(compound: NBTTagCompound) {
        this.width = compound.getShort("Width")
        this.height = compound.getShort("Height")
        this.length = compound.getShort("Length")
        this.blockIds = compound.getByteArray("Blocks")
        this.blockData = compound.getByteArray("Data")
        this.entities = compound.getList("Entities", COMPOUND_ID)
        this.tileEntities = compound.getList("TileEntities", COMPOUND_ID)
        this.blockCount = blockData.size
    }

    @Throws(IOException::class)
    fun loadSchematic(file: File) {
        val stream = openStream(file)
        val compound = readSchematic(stream)
        updateFields(compound)
        stream.close()
    }

    @Throws(IOException::class)
    fun readSchematic(stream: InputStream): NBTTagCompound {
        return NBTCompressedStreamTools.a(stream)
    }

    @Throws(FileNotFoundException::class)
    fun openStream(file: File): InputStream {
        return FileInputStream(file)
    }

}