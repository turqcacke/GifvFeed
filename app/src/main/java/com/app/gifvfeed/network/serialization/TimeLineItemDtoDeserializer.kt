package com.app.gifvfeed.network.serialization

import com.app.gifvfeed.network.entity.TimeLineItemDto
import com.app.gifvfeed.network.entity.TimeLineItemEntryDto
import com.app.gifvfeed.network.entity.TimeLineItemListedDto
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TimeLineItemDtoDeserializer: JsonDeserializer<TimeLineItemDto<Any>> {

//    private val validationMap: HashMap<Class<out Any>, (json: JsonElement?) -> Unit> =
//        hashMapOf(
//            TimeLineItemListedDto::class.java to { json ->
//                run {
//                    json?.asJsonObject?.get("data")?.asJsonArray
//                }
//            },
//
//            TimeLineItemEntryDto::class.java to { json ->
//                run {
//                    json?.asJsonObject?.get("data")?.asJsonObject
//                }
//            },
//        )


    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TimeLineItemDto<Any>? {

        val clazz: Class<out Any>? = when (val type = json?.asJsonObject?.get("type")?.asString) {
            TimeLineItemDto.Type.ENTRY.toString() -> TimeLineItemEntryDto::class.java
            else -> {
                try {
                    TimeLineItemDto.Type.valueOf(type!!.uppercase())
                    json.asJsonObject?.get("data")?.asJsonArray
                    TimeLineItemListedDto::class.java
                } catch (e: java.lang.Exception) {
                    null
                }
            }
        }
//        var clazz: Class<out Any>? = null
//
//        for ((curClazz, validationBlock) in validationMap) {
//            try {
//                validationBlock(json)
//                clazz = curClazz
//                break
//            } catch (e: java.lang.IllegalStateException) {
//                continue
//            }
//        }
        return when (clazz) {
            null -> null
            else -> context?.deserialize(json, clazz)
        }
    }

}