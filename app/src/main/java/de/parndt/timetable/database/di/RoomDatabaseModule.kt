/*
 * *
 *  * Created by Patrick Arndt on 29.10.20 19:49
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 29.10.20 19:45
 *
 */

package de.parndt.timetable.database.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import de.parndt.timetable.database.TimetableDatabase
import de.parndt.timetable.TimetableApplication
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