/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.database.repositories

import de.parndt.timetable.database.TimetableDatabase
import de.parndt.timetable.database.daos.LectureDao
import de.parndt.timetable.database.entities.LectureEntity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LectureRepository @Inject constructor(
    database: TimetableDatabase
) {

    private var lectureDao: LectureDao = database.lectureDao()

    suspend fun insertLecture(lecture: LectureEntity): Long {
        return lectureDao.insertLecture(lecture)
    }

    suspend fun deleteAllLectures() {
        lectureDao.deleteAllLectures()
    }

    suspend fun insertLecture(lecture: List<LectureEntity>): List<Long> {
        return lectureDao.insertLecture(lecture)
    }

    fun getAllLectures(): MutableList<LectureEntity> {
        return lectureDao.getAll()
    }

    fun isTableEmpty(): Boolean {
        return lectureDao.count() == 0
    }
}

