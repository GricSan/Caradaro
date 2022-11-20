package com.gricsan.caradaro.features.userlist.presentation

sealed class UserListScreenEvent {
    object ViewReady: UserListScreenEvent()
    object UserListRefreshRequested: UserListScreenEvent()
}