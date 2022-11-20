package com.gricsan.caradaro.features.location.presentation

import com.gricsan.caradaro.features.location.domain.usecases.GetUserVehiclesLocationsUseCase

data class LocationScreenUseCases(
    val getUserVehiclesLocations: GetUserVehiclesLocationsUseCase
)
