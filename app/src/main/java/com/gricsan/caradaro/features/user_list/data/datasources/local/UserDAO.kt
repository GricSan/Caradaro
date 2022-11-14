package com.gricsan.caradaro.features.user_list.data.datasources.local

import androidx.room.*
import com.gricsan.caradaro.base.data.db.entities.UserEntity
import com.gricsan.caradaro.features.user_list.domain.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Delete
    suspend fun deleteUsers(users: List<UserEntity>)

}