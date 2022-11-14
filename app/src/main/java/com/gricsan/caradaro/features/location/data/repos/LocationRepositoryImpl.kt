package com.gricsan.caradaro.features.location.data.repos

import android.icu.util.Calendar
import com.gricsan.caradaro.base.data.db.daos.VehicleDAO
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.features.location.data.datasources.remote.LocationApiService
import com.gricsan.caradaro.features.location.domain.contracts.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(
    private val vehicleDAO: VehicleDAO,
    private val apiService: LocationApiService
) : LocationRepository {

    companion object {
        private const val LOCATION_DATA_VALIDITY_MILLIS = 30.times(1000L)
    }


    override suspend fun getUserVehiclesLocations(userId: Int): Flow<List<Vehicle>> {
        fetchUserVehiclesLocations(userId)
        return vehicleDAO.getVehiclesByOwnerId(userId)
    }

    override suspend fun getVehicleDetails(vehicleId: Int): Vehicle? {
        return vehicleDAO.getVehicleById(vehicleId)
    }


    private suspend fun fetchUserVehiclesLocations(userId: Int) {
        val expirationTimeStamp = Calendar.getInstance().timeInMillis.plus(LOCATION_DATA_VALIDITY_MILLIS)
        apiService.getUserVehiclesLocations(userId).data
            .filter { it.vehicleId != null }
            .forEach {
                vehicleDAO.updateVehicleLocationData(it.vehicleId!!, it.lat, it.lon, expirationTimeStamp)
            }
    }

}