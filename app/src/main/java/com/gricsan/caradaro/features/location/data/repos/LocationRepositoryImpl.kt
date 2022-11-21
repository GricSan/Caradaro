package com.gricsan.caradaro.features.location.data.repos

import android.icu.util.Calendar
import com.gricsan.caradaro.base.data.db.daos.VehicleDAO
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.features.location.data.datasources.remote.LocationApiService
import com.gricsan.caradaro.features.location.data.datasources.remote.dtos.VehicleLocationDTO
import com.gricsan.caradaro.features.location.domain.contracts.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRepositoryImpl(
    private val vehicleDAO: VehicleDAO,
    private val apiService: LocationApiService
) : LocationRepository {

    companion object {
        private const val LOCATION_DATA_VALIDITY_MILLIS = 30.times(1000L)
    }


    override suspend fun getUserVehiclesLocations(userId: Int): Flow<List<Vehicle>> = flow {
        emit(vehicleDAO.getVehiclesByOwnerId(userId))
        getUserVehiclesLocationsFromRemote(userId).let { locations ->
            cacheLocations(locations)
        }
        emit(vehicleDAO.getVehiclesByOwnerId(userId))
    }

    override suspend fun getVehicleInfo(vehicleId: Int): Vehicle? {
        return vehicleDAO.getVehicleById(vehicleId)
    }


    private suspend fun getUserVehiclesLocationsFromRemote(userId: Int): List<VehicleLocationDTO> {
        return apiService.getUserVehiclesLocations(userId).data
            .filter { it.vehicleId != null }
    }

    private suspend fun cacheLocations(locations: List<VehicleLocationDTO>) {
        val expirationTimeStamp = Calendar.getInstance().timeInMillis.plus(LOCATION_DATA_VALIDITY_MILLIS)
        locations.forEach { locationDTO ->
            vehicleDAO.updateVehicleLocationData(
                locationDTO.vehicleId!!,
                locationDTO.lat,
                locationDTO.lon,
                expirationTimeStamp
            )
        }
    }

}