package com.gricsan.caradaro.features.vehiclelocation.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName

data class VehicleInfoDTO(
    @SerializedName("vehicleid")
    val vehicleid: Int?,
    @SerializedName("make")
    val make: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("year")
    val year: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("vin")
    val vin: String?,
    @SerializedName("foto")
    val foto: String?
)
