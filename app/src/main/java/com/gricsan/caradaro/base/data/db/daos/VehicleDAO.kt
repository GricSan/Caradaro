package com.gricsan.caradaro.base.data.db.daos

import androidx.room.*
import com.gricsan.caradaro.base.data.db.entities.VehicleEntity

@Dao
interface VehicleDAO {

    @Query("SELECT * FROM vehicle WHERE id = :id")
    suspend fun getVehicleById(id: Int): VehicleEntity?

    @Query("SELECT * FROM vehicle")
    suspend fun getVehicles(): List<VehicleEntity>

    @Query("SELECT * FROM vehicle where ownerId = :id")
    suspend fun getVehiclesByOwnerId(id: Int): List<VehicleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicles(vehicles: List<VehicleEntity>)

    @Delete
    suspend fun deleteVehicle(vehicle: VehicleEntity)

    @Delete
    suspend fun deleteVehicles(vehicles: List<VehicleEntity>)

}