package com.gricsan.caradaro.features.vehiclelocation.domain.contracts

import com.gricsan.caradaro.features.vehiclelocation.domain.entities.VehicleGeoData

interface VehicleRepository {
    suspend fun getVehiclesGeoData(userId: Int): Result<List<VehicleGeoData>>
}