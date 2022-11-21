package com.gricsan.caradaro.features.location.domain.usecases

import com.gricsan.caradaro.base.domain.models.Result
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.features.location.domain.contracts.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUserVehiclesLocationsUseCase(
    private val repo: LocationRepository
) {

    operator fun invoke(userId: Int): Flow<Result<List<Vehicle>>> = flow {
        try {
            emit(Result.Loading())
            repo.getUserVehiclesLocations(userId).collect {
                emit(Result.Success(it))
            }
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: "An unexpected error occurred!"))
        } catch (e: IOException) {
            emit(Result.Error("Request failed, please try again later."))
        }
    }

}