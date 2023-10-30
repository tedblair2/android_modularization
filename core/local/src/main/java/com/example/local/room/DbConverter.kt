package com.example.local.room

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DbConverter {

    @TypeConverter
    fun convertListToString(list: List<String?>):String{
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun convertStringToList(value:String):List<String?>{
        return Json.decodeFromString<List<String>>(value)
    }
}