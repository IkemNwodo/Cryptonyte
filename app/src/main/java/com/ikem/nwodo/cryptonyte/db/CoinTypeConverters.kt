package com.ikem.nwodo.cryptonyte.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ikem.nwodo.cryptonyte.model.Social
import java.lang.reflect.Type

object CoinTypeConverters {

    @TypeConverter
    @JvmStatic
    fun stringToStringList(data: String?): List<String>?{
        return data?.split(",")
    }

    @TypeConverter
    @JvmStatic
    fun stringListToString(strings: List<String>?): String? {
        return strings?.joinToString(",")
    }

    @TypeConverter
    @JvmStatic
    fun listToJson(value: List<Social>?): String{
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun jsonToList(value: String): List<Social>?{
        val objects = Gson().fromJson(value, Array<Social>::class.java) as Array<Social>
        return objects.toList()
    }
}