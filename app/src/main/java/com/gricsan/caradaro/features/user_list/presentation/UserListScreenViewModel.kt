package com.gricsan.caradaro.features.user_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gricsan.caradaro.base.domain.models.Result
import com.gricsan.caradaro.features.user_list.presentation.viewstates.UserListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListScreenViewModel @Inject constructor(
    private val useCases: UserListScreenUseCases
) : ViewModel() {

    private val _userListViewState = MutableLiveData(UserListViewState())
    val userListViewState: LiveData<UserListViewState> = _userListViewState


    fun handleEvent(event: UserListScreenEvent) {
        when (event) {
            is UserListScreenEvent.ViewReady -> {
                loadUsers()
            }
            is UserListScreenEvent.UserListRefreshRequested -> {
                loadUsers()
            }
        }
    }


    private fun loadUsers() {
        useCases.getUsers().onEach { result ->
            val newState = when (result) {
                is Result.Success -> {
                    _userListViewState.value!!.copy(
                        data = result.data,
                        error = null,
                        loading = false
                    )
                }
                is Result.Error -> {
                    _userListViewState.value!!.copy(
                        error = result.message,
                        loading = false
                    )
                }
                is Result.Loading -> {
                    _userListViewState.value!!.copy(
                        error = null,
                        loading = true
                    )
                }
            }
            _userListViewState.postValue(newState)
        }.launchIn(viewModelScope)
    }

}