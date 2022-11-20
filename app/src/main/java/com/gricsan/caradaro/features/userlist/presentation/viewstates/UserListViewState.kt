package com.gricsan.caradaro.features.userlist.presentation.viewstates

import com.gricsan.caradaro.features.userlist.domain.models.User

data class UserListViewState(
    val data: List<User> = emptyList(),
    val error: String? = null,
    val loading: Boolean = false
)
