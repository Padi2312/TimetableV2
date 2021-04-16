/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.start.init

import de.parndt.timetable.general.Config
import de.parndt.timetable.general.loader.TimetableLoader
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

class InitUseCase @Inject constructor(
    private val timetableLoader: TimetableLoader,
    private val config: Config
) {

    suspend fun loadAndSaveCourses() {
        timetableLoader.loadAndSaveCourses()
    }

    suspend fun loadAndSaveLectures() {
        val lastUpdated = config.getLastUpdated()
        val date = LocalDateTime.parse(lastUpdated)

        val diff:Double = Duration.between(date, LocalDateTime.now()).seconds.toDouble() / 3600
        if (diff >= 4) {
            timetableLoader.loadAndSaveLectures()
        }
    }

}