package com.gricsan.caradaro.base.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomListConverters {

    companion object {
        private const val EMPTY_ARRAY_JSON = "[]"
    }


    private val gson = Gson()


    @TypeConverter
    fun toListOfIntsJson(list: List<Int>): String {
        return gson.toJson(
            list,
            object : TypeToken<List<Int>>() {}.type
        ) ?: EMPTY_ARRAY_JSON
    }

    @TypeConverter
    fun <T> fromListOfIntsJson(listJson: String): List<Int> {
        return gson.fromJson(
            listJson,
            object : TypeToken<List<Int>>() {}.type
        ) ?: emptyList()
    }

}