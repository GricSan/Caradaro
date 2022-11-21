package com.gricsan.caradaro.features.location.presentation

import androidx.lifecycle.*
import com.gricsan.caradaro.base.domain.models.Result
import com.gricsan.caradaro.features.location.presentation.viewstates.MapViewState
import com.gricsan.caradaro.features.location.presentation.viewstates.VehicleInfoCardViewState
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

    private val _mapViewState = MutableLiveData(MapViewState())
    val mapViewState: LiveData<MapViewState> get() = _mapViewState

    private val _vehicleInfoCardViewState = MutableLiveData(VehicleInfoCardViewState())
    val vehicleInfoCardViewState: LiveData<VehicleInfoCardViewState> get() = _vehicleInfoCardViewState


    fun handleEvent(event: LocationScreenEvent) {
        when (event) {
            is LocationScreenEvent.ViewReady -> {
                savedStateHandle.get<Int>(ARGS_KEY_USER_ID)?.let { loadUserVehiclesLocations(it) }
            }
            is LocationScreenEvent.MapTapped -> {
                val newState = _vehicleInfoCardViewState.value!!.copy(
                    data = null,
                    isShown = false
                )
                _vehicleInfoCardViewState.postValue(newState)
            }
            is LocationScreenEvent.VehicleMarkerSelected -> {
                loadSelectedVehicleInfo(event.vehicleId)
            }
        }
    }


    private fun loadUserVehiclesLocations(userId: Int) {
        useCases.getUserVehiclesLocations(userId).onEach { result ->
            val newState = when (result) {
                is Result.Success -> {
                    _mapViewState.value!!.copy(
                        data = result.data,
                        error = null,
                        loading = false
                    )
                }
                is Result.Error -> {
                    _mapViewState.value!!.copy(
                        error = result.message,
                        loading = false
                    )
                }
                is Result.Loading -> {
                    _mapViewState.value!!.copy(
                        error = null,
                        loading = true
                    )
                }
            }
            _mapViewState.postValue(newState)
        }.launchIn(viewModelScope)
    }

    private fun loadSelectedVehicleInfo(vehicleId: Int) {
        viewModelScope.launch {
            val result = useCases.getVehicleInfo(vehicleId)
            val newState = when (result) {
                is Result.Success -> {
                    _vehicleInfoCardViewState.value!!.copy(
                        data = result.data,
                        error = null,
                        loading = false,
                        isShown = true
                    )
                }
                is Result.Error -> {
                    _vehicleInfoCardViewState.value!!.copy(
                        error = result.message,
                        loading = false,
                        isShown = true
                    )
                }
                is Result.Loading -> {
                    _vehicleInfoCardViewState.value!!.copy(
                        error = null,
                        loading = true,
                        isShown = true
                    )
                }
            }
            _vehicleInfoCardViewState.postValue(newState)
        }
    }

}