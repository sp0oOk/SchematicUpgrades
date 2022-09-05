package com.github.spook.schematics.exclusions

import com.massivecraft.massivecore.xlib.gson.ExclusionStrategy
import com.massivecraft.massivecore.xlib.gson.FieldAttributes

class IgnoreCompanion : ExclusionStrategy {

    override fun shouldSkipField(field: FieldAttributes): Boolean {
        return field.name == "Companion"
    }
    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return false
    }

}