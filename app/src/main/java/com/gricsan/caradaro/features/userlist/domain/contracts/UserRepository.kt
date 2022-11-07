package com.gricsan.caradaro.features.userlist.domain.contracts

import com.gricsan.caradaro.features.userlist.domain.entities.User

interface UserRepository {
    suspend fun getUserList(): Result<List<User>>
}