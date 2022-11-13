package com.gricsan.caradaro.features.user_list.domain.contracts

import com.gricsan.caradaro.features.user_list.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsers(): Flow<List<User>>
}