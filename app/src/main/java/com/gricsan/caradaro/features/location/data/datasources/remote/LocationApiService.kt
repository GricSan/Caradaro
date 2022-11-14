package com.gricsan.caradaro.features.location.data.datasources.remote

import com.gricsan.caradaro.features.location.data.datasources.remote.dtos.GetVehiclesLocationsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("/api/?op=getlocations")
    suspend fun getUserVehiclesLocations(@Query("userid") userId: Int): GetVehiclesLocationsDTO

}