package com.gricsan.caradaro.features.user_list.domain.usecases

import com.gricsan.caradaro.features.user_list.domain.contracts.UserRepository
import com.gricsan.caradaro.features.user_list.domain.entities.User

class GetUserListUseCase(
    private val repo: UserRepository
) {

    suspend operator fun invoke(): List<User> {
        return repo.getUserList().getOrNull() ?: emptyList()
    }

}