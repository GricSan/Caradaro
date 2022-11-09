package com.gricsan.caradaro.features.vehicle_location.data.contracts

import com.gricsan.caradaro.features.vehicle_location.domain.entities.VehicleGeoData

interface VehicleLocalDataSource {
    suspend fun getVehicleGeoData(userId: Int): List<VehicleGeoData>
}