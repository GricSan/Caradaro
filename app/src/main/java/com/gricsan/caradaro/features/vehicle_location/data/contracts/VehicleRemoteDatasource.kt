package com.gricsan.caradaro.features.vehicle_location.data.contracts

import com.gricsan.caradaro.features.vehicle_location.domain.entities.VehicleGeoData

interface VehicleRemoteDatasource {
    suspend fun getVehicleGeoData(opType: String, userId: Int): List<VehicleGeoData>?
}