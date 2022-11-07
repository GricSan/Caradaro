package com.gricsan.caradaro.features.userlist.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName
import com.gricsan.caradaro.features.vehiclelocation.data.datasources.remote.dtos.VehicleInfoDTO

data class UserDTO(
    @SerializedName("userid")
    val userid: Int?,
    @SerializedName("owner")
    val owner: UserInfoDTO?,
    @SerializedName("vehicles")
    val vehicles: List<VehicleInfoDTO>?
)
