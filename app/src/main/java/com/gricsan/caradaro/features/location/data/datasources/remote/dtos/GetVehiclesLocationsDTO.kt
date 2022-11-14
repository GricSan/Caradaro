package com.gricsan.caradaro.features.location.data.datasources.remote.dtos

import com.google.gson.annotations.SerializedName

data class GetVehiclesLocationsDTO(
    @SerializedName("data")
    val data: List<VehicleLocationDTO>
)
