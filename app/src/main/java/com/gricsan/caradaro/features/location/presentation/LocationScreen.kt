package com.gricsan.caradaro.features.location.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gricsan.caradaro.R
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.databinding.FragmentLocationScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationScreen : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentLocationScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LocationScreenViewModel by viewModels()

    private var gMap: GoogleMap? = null
    private var vehicleInfoInAnimation: Animation? = null
    private var vehicleInfoOutAnimation: Animation? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationScreenBinding.inflate(layoutInflater, container, false)

        vehicleInfoInAnimation = AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.anim_vehicle_info_card__slide_in_from_bottom
        )

        vehicleInfoOutAnimation = AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.anim_vehicle_info_card_slide_out_to_bottom
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewState()
        binding.map.getFragment<SupportMapFragment>().getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap.also {
            applyMapConfigurations(it)
            setupInteractions(it)
        }
        viewModel.handleEvent(LocationScreenEvent.ViewReady)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun applyMapConfigurations(googleMap: GoogleMap) {
        googleMap.apply {
            uiSettings.isMapToolbarEnabled = false
        }
    }

    private fun setupInteractions(googleMap: GoogleMap) {
        googleMap.apply {
            setOnMarkerClickListener { marker ->
                vehicleInfoInAnimation?.let { binding.vVehicleInfoCard.startAnimation(it) }
                viewModel.handleEvent(LocationScreenEvent.VehicleMarkerSelected(marker.tag as Int))
                false
            }

            setOnMapClickListener {
                vehicleInfoOutAnimation?.let { binding.vVehicleInfoCard.startAnimation(it) }
            }
        }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LocationScreenViewState.Data -> {
                    markVehiclesOnMap(state.vehicles)
                    state.selectedVehicleInfo?.let {
                        binding.vVehicleInfoCard.setVehicleData(it, "Unknown")
                    }
                }
                is LocationScreenViewState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is LocationScreenViewState.Loading -> {

                }
            }
        }
    }

    private fun markVehiclesOnMap(vehicles: List<Vehicle>) {
        gMap?.apply {
            vehicles.forEachIndexed { index, vehicle ->
                if (vehicle.latitude != null && vehicle.longitude != null) {
                    val coordinates = LatLng(vehicle.latitude, vehicle.longitude)
                    val markerOptions = MarkerOptions()
                        .position(coordinates)
                        .title("Vehicle #${index.plus(1)}")
                    addMarker(markerOptions)?.also { it.tag = vehicle.id }
                }
            }
        }
    }

}