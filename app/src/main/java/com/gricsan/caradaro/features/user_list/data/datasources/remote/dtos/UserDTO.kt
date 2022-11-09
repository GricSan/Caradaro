package com.gricsan.caradaro.features.user_list.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName
import com.gricsan.caradaro.features.user_list.domain.entities.User

data class UserDTO(
    @SerializedName("userid")
    val userid: Int?,
    @SerializedName("owner")
    val owner: UserInfoDTO?,
    @SerializedName("vehicles")
    val vehicles: List<UserVehicleInfoDTO>?
) {

    fun toDomainEntity(): User {
        val userInfo = owner?.toDomainEntity()
        val userVehiclesInfo = vehicles?.map { it.toDomainEntity() }
        return User(userid, userInfo, userVehiclesInfo)
    }

}
