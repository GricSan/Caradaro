package com.gricsan.caradaro.features.vehicle_location.data.datasources.remote

import com.gricsan.caradaro.features.vehicle_location.data.contracts.VehicleRemoteDatasource
import com.gricsan.caradaro.features.vehicle_location.domain.entities.VehicleGeoData
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApiService : VehicleRemoteDatasource {

    @GET("/api")
    override suspend fun getVehicleGeoData(
        @Query("op") opType: String,
        @Query("userId") userId: Int
    ): List<VehicleGeoData>?

}