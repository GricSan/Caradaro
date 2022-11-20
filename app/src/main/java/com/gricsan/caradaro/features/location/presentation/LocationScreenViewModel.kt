package com.gricsan.caradaro.features.location.presentation

import androidx.lifecycle.*
import com.gricsan.caradaro.base.domain.models.Result
import com.gricsan.caradaro.features.location.presentation.viewstates.MapViewState
import com.gricsan.caradaro.features.location.presentation.viewstates.VehicleInfoCardViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
            is LocationScreenEvent.VehicleMarkerSelected -> {
                updateVehicleInfoCardViewState(event.vehicleId)
            }
        }
    }


    private fun loadUserVehiclesLocations(userId: Int) {
        useCases.getUserVehiclesLocations(userId).onEach { result ->
            when (result) {
                is Result.Success -> {

                }
                is Result.Error -> {

                }
                is Result.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateVehicleInfoCardViewState(vehicleId: Int) {

    }

}