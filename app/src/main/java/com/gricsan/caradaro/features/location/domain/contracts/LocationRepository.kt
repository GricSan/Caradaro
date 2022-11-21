package com.gricsan.caradaro.features.location.domain.contracts

import com.gricsan.caradaro.base.domain.models.Vehicle
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getUserVehiclesLocations(userId: Int): Flow<List<Vehicle>>
    suspend fun getVehicleInfo(vehicleId: Int): Vehicle?
}