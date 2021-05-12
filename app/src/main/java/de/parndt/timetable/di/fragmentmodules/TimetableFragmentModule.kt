/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di.fragmentmodules

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.timetable.monthtimetable.ui.TimetableFragment
import de.parndt.timetable.timetable.monthtimetable.ui.TimetableViewModel



@Module
class TimetableFragmentModule {
    @Provides
    fun timetableFragmentModuleViewModel(
        target: TimetableFragment,
        factory: TimetableViewModel.Factory
    ): TimetableViewModel =
        ViewModelProvider(target, factory)[TimetableViewModel::class.java]
}