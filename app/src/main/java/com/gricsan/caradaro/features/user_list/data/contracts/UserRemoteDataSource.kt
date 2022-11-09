package com.gricsan.caradaro.features.user_list.data.contracts

import com.gricsan.caradaro.features.user_list.data.datasources.remote.dtos.GetUserListDTO
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun getUserList(): Response<GetUserListDTO>
}