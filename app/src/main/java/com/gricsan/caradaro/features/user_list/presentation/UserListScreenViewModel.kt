package com.gricsan.caradaro.features.user_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gricsan.caradaro.base.domain.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListScreenViewModel @Inject constructor(
    private val useCases: UserListScreenUseCases
) : ViewModel() {

    private val _viewState = MutableLiveData<UserListScreenViewState>(UserListScreenViewState.Data())
    val viewState: LiveData<UserListScreenViewState> = _viewState


    fun handleEvent(event: UserListScreenEvent) {
        when (event) {
            is UserListScreenEvent.ViewReady -> {
                loadUserList()
            }
            is UserListScreenEvent.UserListRefreshRequested -> {
                loadUserList()
            }
        }
    }


    private fun loadUserList() {
        useCases.getUsers().onEach { result ->
            val newState = when (result) {
                is Result.Success -> {
                    UserListScreenViewState.Data(result.data)
                }
                is Result.Error -> {
                    UserListScreenViewState.Error(result.message)
                }
                is Result.Loading -> {
                    UserListScreenViewState.Loading
                }
            }
            _viewState.postValue(newState)
        }.launchIn(viewModelScope)
    }

}