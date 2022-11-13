package com.gricsan.caradaro.features.user_list.data.repos

import com.gricsan.caradaro.base.data.db.daos.VehicleDAO
import com.gricsan.caradaro.features.user_list.data.datasources.local.UserDAO
import com.gricsan.caradaro.features.user_list.data.datasources.remote.UserApiService
import com.gricsan.caradaro.features.user_list.data.datasources.remote.dtos.UserDTO
import com.gricsan.caradaro.features.user_list.domain.contracts.UserRepository
import com.gricsan.caradaro.features.user_list.domain.models.User
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDAO: UserDAO,
    private val vehicleDAO: VehicleDAO,
    private val apiService: UserApiService
) : UserRepository {

    override suspend fun getUsers(): Flow<List<User>> {
        getUsersFromRemote()
        return userDAO.getUsers()
    }


    private suspend fun getUsersFromRemote() {
        apiService.getUserList().data
            .filter { it.userid != null }
            .also { storeUsers(it) }
            .also { extractAndStoreVehicles(it) }
    }

    private suspend fun storeUsers(users: List<UserDTO>) {
        users
            .map { it.toUserEntity() }
            .also { userDAO.insertUsers(it) }
    }

    private suspend fun extractAndStoreVehicles(users: List<UserDTO>) {
        users.forEach { userDTO ->
            userDTO.vehicles
                .filter { it.vehicleid != null }
                .map { it.toVehicleEntity(userDTO.userid) }
                .also { vehicleDAO.insertVehicles(it) }
        }
    }

}