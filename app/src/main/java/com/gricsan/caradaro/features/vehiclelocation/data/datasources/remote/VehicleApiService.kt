package com.gricsan.caradaro.features.vehiclelocation.data.datasources.remote

import com.gricsan.caradaro.core.network.NetworkResult
import com.gricsan.caradaro.features.vehiclelocation.domain.entities.VehicleGeoData
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApiService :
    com.gricsan.caradaro.features.vehiclelocation.data.contracts.VehicleRemoteDatasource {

    @GET("api/?op=getlocations")
    override suspend fun getVehicleGeoData(
        @Query("userId") userId: Int
    ): NetworkResult<List<VehicleGeoData>>

}