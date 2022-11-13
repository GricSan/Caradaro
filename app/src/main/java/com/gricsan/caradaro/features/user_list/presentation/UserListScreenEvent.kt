package com.gricsan.caradaro.features.user_list.presentation

sealed class UserListScreenEvent {
    object ViewReady: UserListScreenEvent()
    object UserListRefreshRequested: UserListScreenEvent()
}