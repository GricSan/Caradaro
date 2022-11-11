package com.gricsan.caradaro.features.vehicle_location.domain.contracts

import com.gricsan.caradaro.features.vehicle_location.domain.models.VehicleGeoData

interface VehicleRepository {
    suspend fun getVehiclesGeoData(userId: Int): Result<List<VehicleGeoData>>
}