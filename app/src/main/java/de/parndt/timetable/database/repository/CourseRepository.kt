package de.parndt.timetable.database.repository

import de.parndt.timetable.database.TimetableDatabase
import de.parndt.timetable.database.models.CourseEntity
import de.parndt.timetable.database.persistence.CourseDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CourseRepository @Inject constructor(database: TimetableDatabase) {
    private var courseDao: CourseDao = database.courseDao()

    fun getAllCourseNames(): List<String> {
        return courseDao.getAll().map { it.name }
    }

    fun getAllCourses(): List<CourseEntity> {
        return courseDao.getAll()
    }

    fun getCourseByName(courseName: String): CourseEntity {
        return courseDao.getCourseByName(courseName)
    }

    suspend fun insertCourses(courseList: List<CourseEntity>): List<Long> {
        return courseDao.insertCourse(courseList)
    }
}