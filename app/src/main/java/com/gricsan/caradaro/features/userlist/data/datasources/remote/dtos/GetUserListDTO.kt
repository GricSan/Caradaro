package com.gricsan.caradaro.features.userlist.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName

data class GetUserListDTO(
    @SerializedName("data")
    val data: List<UserDTO>
)
