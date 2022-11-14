package com.gricsan.caradaro.base.domain.models

data class Vehicle(
    val id: Int,
    val manufacturerName: String,
    val modelName: String,
    val colorHexValue: String,
    val photoUrl: String,
    val latitude: Double?,
    val longitude: Double?
)
