package com.gricsan.caradaro.features.vehicle_location.presentation

import com.gricsan.caradaro.features.vehicle_location.domain.usecases.GetVehiclesGeoDataUseCase

data class VehicleLocationScreenUseCases(
    val getVehiclesGeoDataUseCase: GetVehiclesGeoDataUseCase
)
