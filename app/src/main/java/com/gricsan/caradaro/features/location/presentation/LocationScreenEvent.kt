package com.gricsan.caradaro.features.location.presentation

sealed class LocationScreenEvent {
    object ViewReady: LocationScreenEvent()

    class VehicleMarkerSelected(
        val vehicleId: Int
    ): LocationScreenEvent()
}
