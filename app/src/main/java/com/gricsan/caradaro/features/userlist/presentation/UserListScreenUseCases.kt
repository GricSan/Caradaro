package com.gricsan.caradaro.features.userlist.presentation

import com.gricsan.caradaro.features.userlist.domain.usecases.GetUsersUseCase

data class UserListScreenUseCases(
    val getUsers: GetUsersUseCase
)
