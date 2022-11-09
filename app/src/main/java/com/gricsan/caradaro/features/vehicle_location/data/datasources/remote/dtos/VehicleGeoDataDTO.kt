package com.gricsan.caradaro.features.vehicle_location.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName

data class VehicleGeoDataDTO(
    @SerializedName("vehicleid")
    val vehicleid: Int?,
    @SerializedName("lat")
    val lat: Float?,
    @SerializedName("lon")
    val lon: Float?
)
