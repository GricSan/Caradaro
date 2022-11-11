package com.gricsan.caradaro.features.user_list.domain.contracts

import com.gricsan.caradaro.features.user_list.domain.models.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}