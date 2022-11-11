package com.gricsan.caradaro.features.vehicle_location.data.contracts

import com.gricsan.caradaro.features.vehicle_location.domain.models.VehicleGeoData

interface VehicleLocalDataSource {
    suspend fun getVehicleGeoData(vehicleId: Int): List<VehicleGeoData>
}