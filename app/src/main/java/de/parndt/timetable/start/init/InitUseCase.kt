package de.parndt.timetable.start.init

import de.parndt.timetable.database.repository.CourseRepository
import de.parndt.timetable.general.loader.TimetableLoader
import javax.inject.Inject

class InitUseCase @Inject constructor(
    private val timetableLoader: TimetableLoader,
) {

    suspend fun loadAndSaveCourses() {
        timetableLoader.loadAndSaveCourses()
    }

    suspend fun loadAndSaveCourseLectures() {
        timetableLoader.loadAndSaveLectures()
    }

}