package com.gricsan.caradaro.features.user_list.data.contracts

import com.gricsan.caradaro.features.user_list.domain.entities.User

interface UserLocalDataSource {
    suspend fun getUserList(): List<User>
    suspend fun getUserById(id: Int): User?
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User)
}