package com.gricsan.caradaro.features.user_list.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gricsan.caradaro.features.user_list.data.datasources.local.daos.UserDAO
import com.gricsan.caradaro.features.user_list.domain.entities.User

@Database(entities = [User::class], version = 1)
@TypeConverters(UserRoomTypeConverters::class)
abstract class UserRoomDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "users_db"
    }


    abstract val userDAO: UserDAO


    suspend fun getUserList(): List<User> {
        TODO("Not yet implemented")
    }

    suspend fun getUserById(id: Int): User? {
        TODO("Not yet implemented")
    }

    suspend fun insertUser(user: User) {
        TODO("Not yet implemented")
    }

    suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }


}