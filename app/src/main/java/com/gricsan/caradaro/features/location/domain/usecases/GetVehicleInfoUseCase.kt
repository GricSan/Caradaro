package com.gricsan.caradaro.features.location.domain.usecases

import com.gricsan.caradaro.base.domain.models.Result
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.features.location.domain.contracts.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetVehicleInfoUseCase(
    private val repo: LocationRepository
) {

    suspend operator fun invoke(vehicleId: Int): Flow<Result<Vehicle>> = flow {
        try {
            emit(Result.Loading())
            val result = repo.getVehicleInfo(vehicleId)
            if (result != null) {
                emit(Result.Success(result))
            } else {
                emit(Result.Error("An unexpected error occurred!"))
            }
        } catch (e: java.lang.Exception) {
            emit(Result.Error("An unexpected error occurred!"))
        }
    }

}