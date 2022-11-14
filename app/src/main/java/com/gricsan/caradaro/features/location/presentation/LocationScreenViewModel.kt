package com.gricsan.caradaro.features.location.presentation

import androidx.lifecycle.*
import com.gricsan.caradaro.base.domain.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @Inject constructor(
    private val useCases: LocationScreenUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val ARGS_KEY_USER_ID = "userId"
    }


    private val _viewState = MutableLiveData<LocationScreenViewState>()
        .apply { value = LocationScreenViewState.Data() }
    val viewState: LiveData<LocationScreenViewState> = _viewState


    fun handleEvent(event: LocationScreenEvent) {
        when (event) {
            is LocationScreenEvent.ViewReady -> {
                savedStateHandle.get<Int>(ARGS_KEY_USER_ID)?.let { loadUserVehiclesLocations(it) }
            }
            is LocationScreenEvent.VehicleMarkerSelected -> {
                loadVehicleDetails(event.vehicleId)
            }
        }
    }


    private fun loadUserVehiclesLocations(userId: Int) {
        useCases.getUserVehiclesLocations(userId).onEach { result ->
            val newState = when(result) {
                is Result.Success -> {
                    LocationScreenViewState.Data(vehicles = result.data)
                }
                is Result.Error -> {
                    LocationScreenViewState.Error(message = result.message)
                }
                is Result.Loading -> {
                    LocationScreenViewState.Loading
                }
            }
            _viewState.postValue(newState)
        }.launchIn(viewModelScope)
    }

    private fun loadVehicleDetails(vehicleId: Int) {
        viewModelScope.launch {
            val newState = when (val result = useCases.getVehicleDetails(vehicleId)) {
                is Result.Success -> {
                    LocationScreenViewState.Data(selectedVehicleInfo = result.data)
                }
                is Result.Error -> {
                    LocationScreenViewState.Error(result.message)
                }
                is Result.Loading -> {
                    LocationScreenViewState.Loading
                }
            }
            _viewState.postValue(newState)
        }
    }

}