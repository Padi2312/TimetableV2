/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di.fragmentmodules

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.timetable.daytimetable.ui.DayTimetableFragment
import de.parndt.timetable.timetable.daytimetable.ui.DayTimetableViewModel


@Module
class DayTimetableFragmentModule {
    @Provides
    fun dayTimetableFragmentModuleViewModel(
        target: DayTimetableFragment,
        factory: DayTimetableViewModel.Factory
    ): DayTimetableViewModel =
        ViewModelProvider(target, factory)[DayTimetableViewModel::class.java]
}