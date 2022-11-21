package com.gricsan.caradaro.features.location.presentation

sealed class LocationScreenEvent {

    object ViewReady: LocationScreenEvent()

    class MapTapped(
        val latitude: Double,
        val longitude: Double
    ): LocationScreenEvent()

    class VehicleMarkerSelected(
        val vehicleId: Int
    ): LocationScreenEvent()

}
