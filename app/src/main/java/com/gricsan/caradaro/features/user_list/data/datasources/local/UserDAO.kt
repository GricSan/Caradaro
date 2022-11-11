package com.gricsan.caradaro.features.user_list.data.datasources.local

import androidx.room.*
import com.gricsan.caradaro.base.data.db.entities.UserEntity

@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): UserEntity?

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Delete
    suspend fun deleteUsers(users: List<UserEntity>)

}