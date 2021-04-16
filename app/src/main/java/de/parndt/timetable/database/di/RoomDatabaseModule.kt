/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.database.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import de.parndt.timetable.TimetableApplication
import de.parndt.timetable.database.TimetableDatabase
import javax.inject.Singleton


@Module
class RoomDatabaseModule(application: TimetableApplication) {

    private var app = application
    private lateinit var timetableDatabase: TimetableDatabase

    @Singleton
    @Provides
    fun provideDatabase(): TimetableDatabase {
        timetableDatabase = Room.databaseBuilder(app, TimetableDatabase::class.java, "timetable")
            .fallbackToDestructiveMigration().build()
        return timetableDatabase
    }


}