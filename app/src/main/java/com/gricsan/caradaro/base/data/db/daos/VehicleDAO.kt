package com.gricsan.caradaro.base.data.db.daos

import androidx.room.*
import com.gricsan.caradaro.base.data.db.entities.VehicleEntity
import com.gricsan.caradaro.base.domain.models.Vehicle

@Dao
interface VehicleDAO {

    @Query("SELECT * FROM vehicle WHERE id = :id")
    suspend fun getVehicleById(id: Int): Vehicle?

    @Query("SELECT * FROM vehicle")
    suspend fun getVehicles(): List<Vehicle>

    @Query("SELECT * FROM vehicle where ownerId = :id")
    suspend fun getVehiclesByOwnerId(id: Int): List<Vehicle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicles(vehicles: List<VehicleEntity>)

    @Delete
    suspend fun deleteVehicle(vehicle: VehicleEntity)

    @Delete
    suspend fun deleteVehicles(vehicles: List<VehicleEntity>)

}