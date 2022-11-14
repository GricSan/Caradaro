package com.gricsan.caradaro.base.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gricsan.caradaro.base.domain.models.Vehicle

@Entity(tableName = "vehicle")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val ownerId: Int,
    val manufacturerName: String,
    val modelName: String,
    val releaseYear: String,
    val colorHexValue: String,
    val vinNumber: String,
    val photoUrl: String,
    val latitude: Double?,
    val longitude: Double?,
    val locationExpirationTimestamp: Long?
) {

    fun toVehicle() = Vehicle(
        id = id,
        manufacturerName = manufacturerName,
        modelName = modelName,
        colorHexValue = colorHexValue,
        photoUrl = photoUrl,
        latitude = latitude,
        longitude = longitude
    )

}