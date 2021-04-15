/*
 * *
 *  * Created by Patrick Arndt on 2020
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 09.11.20 19:33
 *
 */

package de.parndt.timetable.database.di

import dagger.Module
import dagger.Provides
import de.parndt.timetable.database.TimetableDatabase
import de.parndt.timetable.database.repository.LectureRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTodoRepository(database: TimetableDatabase): LectureRepository {
        return LectureRepository(database)
    }

}