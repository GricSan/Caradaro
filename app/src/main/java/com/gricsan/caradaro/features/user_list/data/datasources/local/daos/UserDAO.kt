package com.gricsan.caradaro.features.user_list.data.datasources.local.daos

import androidx.room.*
import com.gricsan.caradaro.features.user_list.data.contracts.UserLocalDataSource
import com.gricsan.caradaro.features.user_list.domain.entities.User

@Dao
interface UserDAO : UserLocalDataSource {

    @Query("SELECT * FROM user")
    override suspend fun getUserList(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    override suspend fun getUserById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertUser(user: User)

    @Delete
    override suspend fun deleteUser(user: User)

}