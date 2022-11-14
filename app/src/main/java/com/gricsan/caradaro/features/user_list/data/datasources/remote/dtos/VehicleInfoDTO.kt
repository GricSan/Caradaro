package com.gricsan.caradaro.features.user_list.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName
import com.gricsan.caradaro.base.data.db.entities.VehicleEntity

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
) {

    fun toVehicleEntity(ownerId: Int?) = VehicleEntity(
        id = vehicleid ?: 0,
        ownerId = ownerId ?: 0,
        manufacturerName = make ?: "",
        modelName = model ?: "",
        releaseYear = year ?: "",
        colorHexValue = color ?: "",
        vinNumber = vin ?: "",
        photoUrl = foto ?: "",
        latitude = null,
        longitude = null,
        locationExpirationTimestamp = null
    )

}