package com.gricsan.caradaro.features.userlist.domain.contracts

import com.gricsan.caradaro.features.userlist.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsers(): Flow<List<User>>
}