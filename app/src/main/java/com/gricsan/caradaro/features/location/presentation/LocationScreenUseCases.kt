package com.gricsan.caradaro.features.location.presentation

import com.gricsan.caradaro.features.location.domain.usecases.GetUserVehiclesLocationsUseCase
import com.gricsan.caradaro.features.location.domain.usecases.GetVehicleInfoUseCase

data class LocationScreenUseCases(
    val getUserVehiclesLocations: GetUserVehiclesLocationsUseCase,
    val getVehicleInfo: GetVehicleInfoUseCase
)
