package com.gricsan.caradaro.features.userlist.domain.usecases

import com.gricsan.caradaro.features.userlist.domain.contracts.UserRepository
import com.gricsan.caradaro.features.userlist.domain.entities.User

class GetUserList(
    private val repo: UserRepository
) {

    suspend fun invoke(): List<User>? {
        TODO("Not yet implemented")
    }

}