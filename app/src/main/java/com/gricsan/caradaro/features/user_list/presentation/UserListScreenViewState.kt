package com.gricsan.caradaro.features.user_list.presentation

import com.gricsan.caradaro.features.user_list.domain.entities.User

data class UserListScreenViewState(
    val userList: List<User> = emptyList()
)
