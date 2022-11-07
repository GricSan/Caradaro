package com.gricsan.caradaro.features.userlist.domain.entities

data class User(
    val id: Int,
    val info: UserInfo,
    val vehicles: List<VehicleInfo>
)
