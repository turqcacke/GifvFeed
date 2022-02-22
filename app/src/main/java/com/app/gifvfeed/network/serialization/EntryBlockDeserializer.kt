package com.app.gifvfeed.network.serialization

import com.app.gifvfeed.network.entity.*
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class EntryBlockDeserializer: JsonDeserializer<EntryBlockBase> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EntryBlockBase? {
        return when(json?.asJsonObject?.get("type")?.asString){
            EntryBlockBase.Type.TEXT.toString() -> context?.deserialize(json, TextEntryBlock::class.java)
            EntryBlockBase.Type.VIDEO.toString() -> context?.deserialize(json, VideoEntryBlock::class.java)
            EntryBlockBase.Type.MEDIA.toString() -> context?.deserialize(json, MediaEntryBlock::class.java)
            else -> null
        }
    }
}