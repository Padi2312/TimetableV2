package de.parndt.timetable.timetable.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.timetable.ui.TimetableFragment
import de.parndt.timetable.timetable.ui.TimetableViewModel



@Module
class TimetableFragmentModule {
    @Provides
    fun timetableFragmentModuleViewModel(
        target: TimetableFragment,
        factory: TimetableViewModel.Factory
    ): TimetableViewModel =
        ViewModelProvider(target, factory)[TimetableViewModel::class.java]
}