package de.parndt.timetable.start.init

import de.parndt.timetable.general.loader.TimetableLoader
import javax.inject.Inject

class InitUseCase @Inject constructor(
    private val timetableLoader: TimetableLoader,
) {

    suspend fun loadAndSaveCourses() {
        timetableLoader.loadAndSaveCourses()
    }

    suspend fun loadAndSaveLectures() {
        timetableLoader.loadAndSaveLectures()
    }

}