package com.gricsan.caradaro.base.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gricsan.caradaro.features.userlist.domain.models.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val surname: String,
    val photoUrl: String,
    val vehiclesIds: List<Int>
) {

    fun toUser() = User(
        id = id,
        name = name,
        surname = surname,
        photoUrl = photoUrl
    )

}