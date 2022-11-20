package com.gricsan.caradaro.features.userlist.data.datasources.remote

import com.gricsan.caradaro.features.userlist.data.datasources.remote.dtos.GetUserListDTO
import retrofit2.http.GET

interface UserApiService {

    @GET("/api/?op=list")
    suspend fun getUserList(): GetUserListDTO

}