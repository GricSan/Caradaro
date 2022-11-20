package com.gricsan.caradaro.features.user_list.presentation.viewstates

import com.gricsan.caradaro.features.user_list.domain.models.User

data class UserListViewState(
    val data: List<User> = emptyList(),
    val error: String? = null,
    val loading: Boolean = false
)
