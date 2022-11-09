package com.gricsan.caradaro.features.user_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListScreenViewModel @Inject constructor(
    private val useCases: UserListScreenUseCases
) : ViewModel() {

    private val _viewState = MutableLiveData(UserListScreenViewState())
    val viewState: LiveData<UserListScreenViewState> = _viewState


    fun handleEvent(event: UserListScreenEvent) {
        when (event) {
            is UserListScreenEvent.ViewReady -> {
                loadUserList()
            }
        }
    }

    private fun loadUserList() {
        viewModelScope.launch {
            val result = useCases.getUserList()
            val newState = _viewState.value?.copy(userList = result)
            _viewState.postValue(newState)
        }
    }

}