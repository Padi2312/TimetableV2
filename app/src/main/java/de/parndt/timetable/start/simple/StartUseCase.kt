package de.parndt.timetable.start.simple

import de.parndt.timetable.database.models.CourseEntity
import de.parndt.timetable.database.repository.CourseRepository
import de.parndt.timetable.general.loader.TimetableLoader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    fun getCourseNames(): List<String> {
        return courseRepository.getAllCourseNames()
    }

    fun getCourseByName(courseName: String): CourseEntity {
        return courseRepository.getCourseByName(courseName)
    }
}