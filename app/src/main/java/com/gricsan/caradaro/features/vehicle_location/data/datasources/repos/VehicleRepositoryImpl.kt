package com.gricsan.caradaro.features.vehicle_location.data.datasources.repos

import com.gricsan.caradaro.features.vehicle_location.data.contracts.VehicleLocalDataSource
import com.gricsan.caradaro.features.vehicle_location.data.contracts.VehicleRemoteDatasource
import com.gricsan.caradaro.features.vehicle_location.domain.contracts.VehicleRepository
import com.gricsan.caradaro.features.vehicle_location.domain.entities.VehicleGeoData

class VehicleRepositoryImpl(
    private val remoteDS: VehicleRemoteDatasource,
    private val localDS: VehicleLocalDataSource
) : VehicleRepository {

    override suspend fun getVehiclesGeoData(userId: Int): Result<List<VehicleGeoData>> {
        TODO("Not yet implemented")
    }

}