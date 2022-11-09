package com.gricsan.caradaro.features.user_list.presentation

import com.gricsan.caradaro.features.user_list.domain.usecases.GetUserListUseCase

data class UserListScreenUseCases(
    val getUserList: GetUserListUseCase
)
