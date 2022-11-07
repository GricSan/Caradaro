package com.gricsan.caradaro.features.vehiclelocation.data.repos

import com.gricsan.caradaro.features.vehiclelocation.data.contracts.VehicleLocalDataSource
import com.gricsan.caradaro.features.vehiclelocation.data.contracts.VehicleRemoteDatasource
import com.gricsan.caradaro.features.vehiclelocation.domain.contracts.VehicleRepository
import com.gricsan.caradaro.features.vehiclelocation.domain.entities.VehicleGeoData

class VehicleRepositoryImpl(
    private val remoteDS: VehicleRemoteDatasource,
    private val localDS: VehicleLocalDataSource
) : VehicleRepository {

    override suspend fun getVehiclesGeoData(userId: Int): Result<List<VehicleGeoData>> {
        TODO("Not yet implemented")
    }

}