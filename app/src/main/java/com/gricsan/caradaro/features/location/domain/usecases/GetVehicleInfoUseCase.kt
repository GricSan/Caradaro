package com.gricsan.caradaro.features.location.domain.usecases

import com.gricsan.caradaro.base.domain.models.Result
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.features.location.domain.contracts.LocationRepository

class GetVehicleInfoUseCase(
    private val repo: LocationRepository
) {

    suspend operator fun invoke(vehicleId: Int): Result<Vehicle> {
        return try {
            val result = repo.getVehicleInfo(vehicleId)
            if (result != null) {
                Result.Success(result)
            } else {
                Result.Error("An unexpected error occurred!")
            }
        } catch (e: java.lang.Exception) {
            Result.Error("An unexpected error occurred!")
        }
    }

}