package com.gricsan.caradaro.features.user_list.domain.contracts

import com.gricsan.caradaro.features.user_list.domain.entities.User

interface UserRepository {
    suspend fun getUserList(): Result<List<User>>
}