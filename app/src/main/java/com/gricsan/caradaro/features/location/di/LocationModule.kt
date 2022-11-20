package com.gricsan.caradaro.features.location.di

import com.gricsan.caradaro.base.data.db.daos.VehicleDAO
import com.gricsan.caradaro.features.location.data.datasources.remote.LocationApiService
import com.gricsan.caradaro.features.location.data.repos.LocationRepositoryImpl
import com.gricsan.caradaro.features.location.domain.contracts.LocationRepository
import com.gricsan.caradaro.features.location.domain.usecases.GetUserVehiclesLocationsUseCase
import com.gricsan.caradaro.features.location.presentation.LocationScreenUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object LocationModule {

    @Provides
    @ViewModelScoped
    fun provideLocationUseCases(repository: LocationRepository): LocationScreenUseCases {
        return LocationScreenUseCases(
            getUserVehiclesLocations = GetUserVehiclesLocationsUseCase(repository)
        )
    }

    @Provides
    @ViewModelScoped
    fun provideLocationRepository(
        vehicleDAO: VehicleDAO,
        apiService: LocationApiService
    ): LocationRepository {
        return LocationRepositoryImpl(
            vehicleDAO = vehicleDAO,
            apiService = apiService
        )
    }

    @Provides
    @ViewModelScoped
    fun provideLocationApiService(retrofit: Retrofit): LocationApiService {
        return retrofit.create(LocationApiService::class.java)
    }

}