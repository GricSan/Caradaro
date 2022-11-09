package com.gricsan.caradaro.features.user_list.data.datasources.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gricsan.caradaro.features.user_list.domain.entities.UserInfo
import com.gricsan.caradaro.features.user_list.domain.entities.UserVehicleInfo

class UserRoomTypeConverters {

    companion object {
        private const val EMPTY_ARRAY_JSON = "[]"
    }

    private val gson = Gson()


    @TypeConverter
    fun toUserInfoJson(userInfo: UserInfo): String {
        return gson.toJson(userInfo, userInfo::class.java)
    }

    @TypeConverter
    fun fromUserInfoJson(userInfoJson: String): UserInfo {
        return gson.fromJson(userInfoJson, UserInfo::class.java)
    }

    @TypeConverter
    fun toUserVehicleInfoJson(userVehicleInfo: List<UserVehicleInfo>): String {
        return gson.toJson(
            userVehicleInfo,
            object : TypeToken<Array<UserVehicleInfo>>() {}.type
        ) ?: EMPTY_ARRAY_JSON
    }

    @TypeConverter
    fun fromUserVehicleInfoJson(userVehicleInfoJson: String): List<UserVehicleInfo> {
        return gson.fromJson(
            userVehicleInfoJson,
            object : TypeToken<Array<UserVehicleInfo>>() {}.type
        ) ?: emptyList()
    }

}