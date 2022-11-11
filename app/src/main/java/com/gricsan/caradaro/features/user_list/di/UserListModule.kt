package com.gricsan.caradaro.features.user_list.di

import com.gricsan.caradaro.base.data.db.AppRoomDatabase
import com.gricsan.caradaro.base.data.db.daos.VehicleDAO
import com.gricsan.caradaro.features.user_list.data.datasources.local.UserDAO
import com.gricsan.caradaro.features.user_list.data.datasources.remote.UserApiService
import com.gricsan.caradaro.features.user_list.data.repos.UserRepositoryImpl
import com.gricsan.caradaro.features.user_list.domain.contracts.UserRepository
import com.gricsan.caradaro.features.user_list.domain.usecases.GetUsersUseCase
import com.gricsan.caradaro.features.user_list.presentation.UserListScreenUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object UserListModule {

    @Provides
    @ViewModelScoped
    fun provideUserListUseCases(repository: UserRepository): UserListScreenUseCases {
        return UserListScreenUseCases(
            getUsers = GetUsersUseCase(repository)
        )
    }

    @Provides
    @ViewModelScoped
    fun provideUserRepository(
        userDAO: UserDAO,
        vehicleDAO: VehicleDAO,
        apiService: UserApiService,
    ): UserRepository {
        return UserRepositoryImpl(
            userDAO = userDAO,
            vehicleDAO = vehicleDAO,
            apiService = apiService
        )
    }

    @Provides
    @ViewModelScoped
    fun provideUserDAO(database: AppRoomDatabase): UserDAO {
        return database.userDAO
    }

    @Provides
    @ViewModelScoped
    fun provideVehicleDAO(database: AppRoomDatabase): VehicleDAO {
        return database.vehicleDAO
    }

    @Provides
    @ViewModelScoped
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

}