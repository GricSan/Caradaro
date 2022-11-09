package com.gricsan.caradaro.features.vehicle_location.domain.usecases

import com.gricsan.caradaro.features.vehicle_location.domain.contracts.VehicleRepository
import com.gricsan.caradaro.features.vehicle_location.domain.entities.VehicleGeoData

class GetVehiclesGeoDataUseCase(
    private val repo: VehicleRepository
) {

    suspend fun invoke(): List<VehicleGeoData>? {
        TODO("Not yet implemented")
    }

}