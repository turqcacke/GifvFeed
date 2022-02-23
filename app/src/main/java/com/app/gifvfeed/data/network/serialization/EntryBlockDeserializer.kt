package com.app.gifvfeed.data.network.serialization

import com.app.gifvfeed.data.network.entity.*
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
            EntryBlockBase.Type.TEXT.toString() -> context?.deserialize(json, TextEntryBlockDto::class.java)
            EntryBlockBase.Type.VIDEO.toString() -> context?.deserialize(json, VideoEntryBlockDto::class.java)
            EntryBlockBase.Type.MEDIA.toString() -> context?.deserialize(json, MediaEntryBlockDto::class.java)
            else -> null
        }
    }
}