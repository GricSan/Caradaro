package com.gricsan.caradaro.features.user_list.di

import android.app.Application
import androidx.room.Room
import com.gricsan.caradaro.features.user_list.data.contracts.UserLocalDataSource
import com.gricsan.caradaro.features.user_list.data.contracts.UserRemoteDataSource
import com.gricsan.caradaro.features.user_list.data.datasources.local.UserRoomDatabase
import com.gricsan.caradaro.features.user_list.data.datasources.remote.UserApiService
import com.gricsan.caradaro.features.user_list.data.repos.UserRepositoryImpl
import com.gricsan.caradaro.features.user_list.domain.contracts.UserRepository
import com.gricsan.caradaro.features.user_list.domain.usecases.GetUserListUseCase
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
            getUserList = GetUserListUseCase(repository)
        )
    }

    @Provides
    @ViewModelScoped
    fun provideUserRepository(
        localDataSource: UserLocalDataSource,
        remoteDataSource: UserRemoteDataSource,
    ): UserRepository {
        return UserRepositoryImpl(
            localDS = localDataSource,
            remoteDS = remoteDataSource
        )
    }

    @Provides
    @ViewModelScoped
    fun provideUserLocalDataSource(app: Application): UserLocalDataSource {
        return Room.databaseBuilder(
            app,
            UserRoomDatabase::class.java,
            UserRoomDatabase.DATABASE_NAME
        ).build().userDAO
    }

    @Provides
    @ViewModelScoped
    fun provideUserRemoteDataSource(retrofit: Retrofit): UserRemoteDataSource {
        return retrofit.create(UserApiService::class.java)
    }

}