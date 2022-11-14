package com.gricsan.caradaro.features.location.presentation

import com.gricsan.caradaro.base.domain.models.Vehicle

sealed class LocationScreenViewState {

    data class Data(
        val vehicles: List<Vehicle> = emptyList(),
        val selectedVehicleInfo: Vehicle? = null
    ) : LocationScreenViewState()

    data class Error(
        val message: String
    ) : LocationScreenViewState()

    object Loading : LocationScreenViewState()

}
