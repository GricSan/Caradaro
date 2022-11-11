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
    val photoUrl: String
) {

    fun toVehicle() = Vehicle(
        id = id,
        modelName = modelName,
        colorHexValue = colorHexValue,
        photoUrl = photoUrl
    )

}