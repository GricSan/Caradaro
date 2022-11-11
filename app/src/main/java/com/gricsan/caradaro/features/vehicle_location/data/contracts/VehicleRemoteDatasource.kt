package com.gricsan.caradaro.features.vehicle_location.data.contracts

import com.gricsan.caradaro.features.vehicle_location.domain.models.VehicleGeoData

interface VehicleRemoteDatasource {
    suspend fun getVehicleGeoData(userId: Int): List<VehicleGeoData>?
}