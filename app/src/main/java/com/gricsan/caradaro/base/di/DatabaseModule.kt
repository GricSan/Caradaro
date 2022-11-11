package com.gricsan.caradaro.base.di

import android.app.Application
import androidx.room.Room
import com.gricsan.caradaro.base.data.db.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppRoomDatabase(app: Application): AppRoomDatabase {
        return Room.databaseBuilder(
            app,
            AppRoomDatabase::class.java,
            AppRoomDatabase.DATABASE_NAME
        ).build()
    }

}