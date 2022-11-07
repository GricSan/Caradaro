package com.gricsan.caradaro.features.userlist.data.datasources.remote

import com.gricsan.caradaro.core.network.NetworkResult
import com.gricsan.caradaro.features.userlist.data.contracts.UserRemoteDataSource
import com.gricsan.caradaro.features.userlist.domain.entities.User
import retrofit2.http.GET

interface UserApiService : UserRemoteDataSource {

    @GET("api/?op=list")
    override suspend fun getUserList(): NetworkResult<List<User>>

}