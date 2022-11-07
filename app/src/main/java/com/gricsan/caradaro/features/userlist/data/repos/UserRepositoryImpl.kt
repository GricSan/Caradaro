package com.gricsan.caradaro.features.userlist.data.repos

import com.gricsan.caradaro.features.userlist.data.contracts.UserLocalDataSource
import com.gricsan.caradaro.features.userlist.data.contracts.UserRemoteDataSource
import com.gricsan.caradaro.features.userlist.domain.contracts.UserRepository
import com.gricsan.caradaro.features.userlist.domain.entities.User

class UserRepositoryImpl(
    private val remoteDS: UserRemoteDataSource,
    private val localDS: UserLocalDataSource
) : UserRepository {

    override suspend fun getUserList(): Result<List<User>> {
        TODO("Not yet implemented")
    }

}