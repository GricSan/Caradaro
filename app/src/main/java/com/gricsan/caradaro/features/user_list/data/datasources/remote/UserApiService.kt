package com.gricsan.caradaro.features.user_list.data.datasources.remote

import com.gricsan.caradaro.features.user_list.data.contracts.UserRemoteDataSource
import com.gricsan.caradaro.features.user_list.data.datasources.remote.dtos.GetUserListDTO
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService : UserRemoteDataSource {

    @GET("/api?op=list")
    override suspend fun getUserList(): Response<GetUserListDTO>

}