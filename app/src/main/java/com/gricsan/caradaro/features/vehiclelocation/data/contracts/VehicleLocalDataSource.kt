package com.gricsan.caradaro.features.vehiclelocation.data.contracts

import com.gricsan.caradaro.features.vehiclelocation.domain.entities.VehicleGeoData

interface VehicleLocalDataSource {
    suspend fun getVehicleGeoData(userId: Int): List<VehicleGeoData>
}