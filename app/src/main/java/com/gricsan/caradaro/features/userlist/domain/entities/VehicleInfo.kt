package com.gricsan.caradaro.features.userlist.domain.entities

data class VehicleInfo(
    val id: Long,
    val manufacturerName: String,
    val modelName: String,
    val releaseYear: String,
    val colorHexValue: String,
    val vinNumber: String,
    val photoUrl: String
)
