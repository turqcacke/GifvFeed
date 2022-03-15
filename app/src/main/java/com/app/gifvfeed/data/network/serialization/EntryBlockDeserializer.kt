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
            EntryBlockBase.Type.HEADER.toString() -> context?.deserialize(json, TextEntryBlockDto::class.java)
            EntryBlockBase.Type.TEXT.toString() -> context?.deserialize(json, TextEntryBlockDto::class.java)
            EntryBlockBase.Type.VIDEO.toString() -> context?.deserialize(json, YouTubeVideoEntryBlockDto::class.java)
            EntryBlockBase.Type.MEDIA.toString() -> context?.deserialize(json, MediaEntryBlockDto::class.java)
            EntryBlockBase.Type.INSTAGRAM.toString() -> context?.deserialize(json, InstagramEntryBlockDto::class.java)
            else -> null
        }
    }
}