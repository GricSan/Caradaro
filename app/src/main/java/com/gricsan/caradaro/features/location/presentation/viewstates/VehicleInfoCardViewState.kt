package com.gricsan.caradaro.features.location.presentation.viewstates

import com.gricsan.caradaro.base.domain.models.Vehicle

data class VehicleInfoCardViewState(
    val data: Vehicle? = null,
    val error: String? = null,
    val loading: Boolean = false
)