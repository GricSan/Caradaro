package com.gricsan.caradaro.features.user_list.presentation

import com.gricsan.caradaro.features.user_list.domain.models.User

sealed class UserListScreenViewState {

    data class Data(
        val userList: List<User> = emptyList()
    ) : UserListScreenViewState()

    data class Error(
        val message: String
    ) : UserListScreenViewState()

    object Loading : UserListScreenViewState()

}
