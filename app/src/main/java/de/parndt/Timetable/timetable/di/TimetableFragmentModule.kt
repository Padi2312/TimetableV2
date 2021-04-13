package de.parndt.Timetable.timetable.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.Timetable.timetable.ui.TimetableFragment
import de.parndt.Timetable.timetable.ui.TimetableViewModel



@Module
class TimetableFragmentModule {
    @Provides
    fun timetableFragmentModuleViewModel(
        target: TimetableFragment,
        factory: TimetableViewModel.Factory
    ): TimetableViewModel =
        ViewModelProvider(target, factory)[TimetableViewModel::class.java]
}