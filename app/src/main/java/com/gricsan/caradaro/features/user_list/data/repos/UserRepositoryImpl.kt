package com.gricsan.caradaro.features.user_list.data.repos

import com.gricsan.caradaro.base.data.db.daos.VehicleDAO
import com.gricsan.caradaro.features.user_list.data.datasources.local.UserDAO
import com.gricsan.caradaro.features.user_list.data.datasources.remote.UserApiService
import com.gricsan.caradaro.features.user_list.domain.contracts.UserRepository
import com.gricsan.caradaro.features.user_list.domain.models.User

class UserRepositoryImpl(
    private val userDAO: UserDAO,
    private val vehicleDAO: VehicleDAO,
    private val apiService: UserApiService
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        val users = apiService.getUserList().data
            .filter { it.userid != null }
            .onEach { userDTO ->
                userDTO.vehicles
                    ?.map { it.toVehicleEntity(userDTO.userid) }
                    ?.also { vehicleDAO.insertVehicles(it) }
            }
            .map { it.toUserEntity() }
            .also { userDAO.insertUsers(it) }
        return users.map { it.toUser() }
    }

}