package com.gricsan.caradaro.features.vehicle_location.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VehicleLocationScreenViewModel : ViewModel() {

    private val _viewState = MutableLiveData(VehicleLocationScreenViewState())
    val viewState: LiveData<VehicleLocationScreenViewState> = _viewState


    fun handleEvent(event: VehicleLocationScreenEvent) {
        when (event) {
            is VehicleLocationScreenEvent.ViewReady -> {

            }
        }
    }

}