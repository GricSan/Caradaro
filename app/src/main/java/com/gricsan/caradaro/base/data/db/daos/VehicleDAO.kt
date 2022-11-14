package com.gricsan.caradaro.base.data.db.daos

import androidx.room.*
import com.gricsan.caradaro.base.data.db.entities.VehicleEntity
import com.gricsan.caradaro.base.domain.models.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDAO {

    @Query("SELECT * FROM vehicle WHERE id = :vehicleId")
    suspend fun getVehicleById(vehicleId: Int): Vehicle?

    @Query("SELECT * FROM vehicle")
    fun getVehicles(): Flow<List<Vehicle>>

    @Query("SELECT * FROM vehicle where ownerId = :ownerId")
    fun getVehiclesByOwnerId(ownerId: Int): Flow<List<Vehicle>>

    @Query(
        "UPDATE vehicle SET " +
        "latitude = :latitude," +
        "longitude = :longitude," +
        "locationExpirationTimestamp = :expirationTimestamp " +
        "WHERE id = :vehicleId"
    )
    suspend fun updateVehicleLocationData(
        vehicleId: Int,
        latitude: Double?,
        longitude: Double?,
        expirationTimestamp: Long?
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicles(vehicles: List<VehicleEntity>)

    @Delete
    suspend fun deleteVehicle(vehicle: VehicleEntity)

    @Delete
    suspend fun deleteVehicles(vehicles: List<VehicleEntity>)

}