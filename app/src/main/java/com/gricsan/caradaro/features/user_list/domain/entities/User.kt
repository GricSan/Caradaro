package com.gricsan.caradaro.features.user_list.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val info: UserInfo?,
    val vehicles: List<UserVehicleInfo>?
)