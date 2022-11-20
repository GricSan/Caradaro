package com.gricsan.caradaro.features.userlist.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName
import com.gricsan.caradaro.base.data.db.entities.UserEntity

data class UserDTO(
    @SerializedName("userid")
    val userid: Int?,
    @SerializedName("owner")
    val owner: UserInfoDTO?,
    @SerializedName("vehicles")
    val vehicles: List<VehicleInfoDTO>
) {

    fun toUserEntity() = UserEntity(
        id = userid ?: 0,
        name = owner?.name ?: "",
        surname = owner?.surname ?: "",
        photoUrl = owner?.foto ?: "",
        vehiclesIds = vehicles.map { it.vehicleid ?: 0 }
    )

}
