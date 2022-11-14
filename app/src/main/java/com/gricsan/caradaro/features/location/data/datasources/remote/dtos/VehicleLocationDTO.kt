package com.gricsan.caradaro.features.location.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName

data class VehicleLocationDTO(
    @SerializedName("vehicleid")
    val vehicleId: Int?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?
)