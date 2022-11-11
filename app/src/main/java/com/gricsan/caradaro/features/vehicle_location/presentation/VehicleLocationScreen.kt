package com.gricsan.caradaro.features.vehicle_location.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gricsan.caradaro.databinding.FragmentVehicleLocationScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleLocationScreen : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentVehicleLocationScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VehicleLocationScreenViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleLocationScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.map.getFragment<SupportMapFragment>().getMapAsync(this)
        setupInteractions()
        observeViewState()
    }

    override fun onStart() {
        super.onStart()
        viewModel.handleEvent(VehicleLocationScreenEvent.ViewReady)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupInteractions() {

    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { newState ->
            updateViewState(newState)
        }
    }

    private fun updateViewState(newState: VehicleLocationScreenViewState) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}