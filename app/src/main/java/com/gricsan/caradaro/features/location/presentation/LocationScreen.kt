package com.gricsan.caradaro.features.location.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.databinding.FragmentLocationScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationScreen : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentLocationScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationScreenViewModel by viewModels()

    private var gMap: GoogleMap? = null

    private var errorToast: Toast? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.map.getFragment<SupportMapFragment>().getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap.apply {
            applyMapConfigurations(this)
            setupMapInteractions(this)
        }
        observeViewState()
        viewModel.handleEvent(LocationScreenEvent.ViewReady)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun applyMapConfigurations(googleMap: GoogleMap) {
        with(googleMap) {
            uiSettings.isMapToolbarEnabled = false
        }
    }

    private fun setupMapInteractions(googleMap: GoogleMap) {
        with(googleMap) {
            setOnMarkerClickListener { marker ->
                viewModel.handleEvent(LocationScreenEvent.VehicleMarkerSelected(marker.tag as Int))
                false
            }

            setOnMapClickListener {
                viewModel.handleEvent(LocationScreenEvent.MapTapped(it.latitude, it.longitude))
            }
        }
    }

    private fun observeViewState() {
        with(viewModel) {
            mapViewState.observe(viewLifecycleOwner) { state ->
                // Hide displayed error (if any)
                errorToast?.cancel()
                // Update loading state
                binding.progress.isVisible = state.loading
                // Set data
                if (state.data.isNotEmpty()) {
                    markVehiclesOnMap(state.data)
                }
                // Display error (if any)
                state.error?.let { message ->
                    errorToast = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
                        .also { it.show() }
                }
            }

            vehicleInfoCardViewState.observe(viewLifecycleOwner) { state ->
                // Update Vehicle info card's visibility
                binding.vVehicleInfoCard.isVisible = state.isShown
                // Set data
                state.data?.let { binding.vVehicleInfoCard.setVehicleData(it) }
            }
        }
    }

    private fun markVehiclesOnMap(vehicles: List<Vehicle>) {
        gMap?.let { map ->
            map.clear()
            vehicles.forEachIndexed { index, vehicle ->
                if (vehicle.latitude != null && vehicle.longitude != null) {
                    val coordinates = LatLng(vehicle.latitude, vehicle.longitude)
                    val markerOptions = MarkerOptions()
                        .position(coordinates)
                        .title("Vehicle #${index.plus(1)}")
                    map.addMarker(markerOptions)?.also { it.tag = vehicle.id }
                }
            }
        }
    }

}