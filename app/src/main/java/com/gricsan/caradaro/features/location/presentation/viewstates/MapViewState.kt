package com.gricsan.caradaro.features.location.presentation.viewstates

import com.gricsan.caradaro.base.domain.models.Vehicle

data class MapViewState(
    val data: List<Vehicle> = emptyList(),
    val error: String? = null,
    val loading: Boolean = false
)