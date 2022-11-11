package com.gricsan.caradaro.features.user_list.presentation

import com.gricsan.caradaro.features.user_list.domain.models.User

sealed class UserListScreenViewState {

    class Data(
        val userList: List<User> = emptyList()
    ) : UserListScreenViewState()

    class Error(
        val message: String
    ) : UserListScreenViewState()

    object Loading : UserListScreenViewState()

}
