package com.gricsan.caradaro.features.userlist.data.contracts

import com.gricsan.caradaro.core.network.NetworkResult
import com.gricsan.caradaro.features.userlist.domain.entities.User

interface UserRemoteDataSource {
    suspend fun getUserList(): NetworkResult<List<User>>
}