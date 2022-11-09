package com.gricsan.caradaro.features.user_list.data.repos

import com.gricsan.caradaro.features.user_list.data.contracts.UserLocalDataSource
import com.gricsan.caradaro.features.user_list.data.contracts.UserRemoteDataSource
import com.gricsan.caradaro.features.user_list.domain.contracts.UserRepository
import com.gricsan.caradaro.features.user_list.domain.entities.User

class UserRepositoryImpl(
    private val localDS: UserLocalDataSource,
    private val remoteDS: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUserList(): Result<List<User>> {
        val networkResult = remoteDS.getUserList()

        return if (networkResult.isSuccessful) {
            val userList = networkResult.body()?.data
                ?.map { it.toDomainEntity() }
                ?.filter { it.id != null } ?: emptyList()
            Result.success(userList)
        } else {
            Result.failure(Exception("Failed to fetch data"))
        }
    }

}