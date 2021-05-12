/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.init

import de.parndt.timetable.general.TimetableLoader
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