package com.gricsan.caradaro.features.userlist.data.contracts

import com.gricsan.caradaro.features.userlist.domain.entities.User

interface UserLocalDataSource {
    suspend fun getUserList(): List<User>
}