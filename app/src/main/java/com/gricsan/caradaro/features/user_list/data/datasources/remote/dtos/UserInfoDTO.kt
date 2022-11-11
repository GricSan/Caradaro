package com.gricsan.caradaro.features.user_list.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName

data class UserInfoDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("foto")
    val foto: String?
)
