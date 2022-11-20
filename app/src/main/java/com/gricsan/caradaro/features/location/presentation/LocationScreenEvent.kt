package com.gricsan.caradaro.features.location.presentation

sealed class LocationScreenEvent {

    class VehicleMarkerSelected(
        val vehicleId: Int
    ): LocationScreenEvent()

    object ViewReady: LocationScreenEvent()
}
