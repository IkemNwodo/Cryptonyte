package com.ikem.nwodo.cryptonyte.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.History
import com.ikem.nwodo.cryptonyte.db.model.Social

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

    @TypeConverter
    @JvmStatic
    fun CoinlistToJson(value: List<Coin>?): String{
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun jsonToListCoin(value: String): List<Coin>?{
        val objects = Gson().fromJson(value, Array<Coin>::class.java) as Array<Coin>?
        return objects?.toList()
    }

    @TypeConverter
    @JvmStatic
    fun HistorylistToJson(value: List<History>?): String{
        return Gson().toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun jsonToListHistory(value: String): List<History>?{
        val objects = Gson().fromJson(value, Array<History>::class.java) as Array<History>
        return objects.toList()
    }

}