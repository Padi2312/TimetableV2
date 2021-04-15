/*
 * *
 *  * Created by Patrick Arndt on 2020
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 09.11.20 19:39
 *
 */

package de.parndt.timetable.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.parndt.timetable.database.models.CourseEntity
import de.parndt.timetable.database.models.LectureEntity
import de.parndt.timetable.database.persistence.CourseDao
import de.parndt.timetable.database.persistence.LectureDao


@Database(
    entities = [
        LectureEntity::class,
        CourseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TimetableDatabase : RoomDatabase() {
    abstract fun lectureDao(): LectureDao
    abstract fun courseDao(): CourseDao

}