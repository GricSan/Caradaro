package com.gricsan.caradaro.features.vehiclelocation.domain.usecases

import com.gricsan.caradaro.features.vehiclelocation.domain.contracts.VehicleRepository
import com.gricsan.caradaro.features.vehiclelocation.domain.entities.VehicleGeoData

class GetVehiclesGeoData(
    private val repo: VehicleRepository
) {

    suspend fun invoke(): List<VehicleGeoData>? {
        TODO("Not yet implemented")
    }

}