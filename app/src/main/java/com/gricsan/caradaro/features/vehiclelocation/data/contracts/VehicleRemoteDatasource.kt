package com.gricsan.caradaro.features.vehiclelocation.data.contracts

import com.gricsan.caradaro.core.network.NetworkResult
import com.gricsan.caradaro.features.vehiclelocation.domain.entities.VehicleGeoData

interface VehicleRemoteDatasource {
    suspend fun getVehicleGeoData(userId: Int): NetworkResult<List<VehicleGeoData>>
}