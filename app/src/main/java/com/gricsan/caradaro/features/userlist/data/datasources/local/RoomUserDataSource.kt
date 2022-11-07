package com.gricsan.caradaro.features.userlist.data.datasources.local

import com.gricsan.caradaro.features.userlist.data.contracts.UserLocalDataSource
import com.gricsan.caradaro.features.userlist.domain.entities.User

class RoomUserDataSource : UserLocalDataSource {

    override suspend fun getUserList(): List<User> {
        TODO("Not yet implemented")
    }

}