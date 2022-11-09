package com.gricsan.caradaro.features.user_list.domain.entities

data class UserVehicleInfo(
    val id: Int?,
    val manufacturerName: String?,
    val modelName: String?,
    val releaseYear: String?,
    val colorHexValue: String?,
    val vinNumber: String?,
    val photoUrl: String?
)