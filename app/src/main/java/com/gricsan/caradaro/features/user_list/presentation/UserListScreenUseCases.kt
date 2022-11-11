package com.gricsan.caradaro.features.user_list.presentation

import com.gricsan.caradaro.features.user_list.domain.usecases.GetUsersUseCase

data class UserListScreenUseCases(
    val getUsers: GetUsersUseCase
)
