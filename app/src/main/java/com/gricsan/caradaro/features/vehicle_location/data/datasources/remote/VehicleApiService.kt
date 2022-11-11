package com.gricsan.caradaro.features.vehicle_location.data.datasources.remote

import com.gricsan.caradaro.features.vehicle_location.data.contracts.VehicleRemoteDatasource
import com.gricsan.caradaro.features.vehicle_location.domain.models.VehicleGeoData
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApiService : VehicleRemoteDatasource {

    @GET("/api?op=getlocations")
    override suspend fun getVehicleGeoData(@Query("userId") userId: Int): List<VehicleGeoData>

}