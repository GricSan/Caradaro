package com.gricsan.caradaro.features.user_list.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName
import com.gricsan.caradaro.features.user_list.domain.entities.UserVehicleInfo

data class UserVehicleInfoDTO(
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

    fun toDomainEntity(): UserVehicleInfo {
        return UserVehicleInfo(vehicleid, make, model, year, color, vin, foto)
    }

}
