package com.gricsan.caradaro.base.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gricsan.caradaro.base.data.db.converters.RoomListConverters
import com.gricsan.caradaro.base.data.db.daos.UserDAO
import com.gricsan.caradaro.base.data.db.daos.VehicleDAO
import com.gricsan.caradaro.base.data.db.entities.UserEntity
import com.gricsan.caradaro.base.data.db.entities.VehicleEntity

@Database(
    entities = [UserEntity::class, VehicleEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomListConverters::class)
abstract class AppRoomDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_db"
    }


    abstract val userDAO: UserDAO
    abstract val vehicleDAO: VehicleDAO

}