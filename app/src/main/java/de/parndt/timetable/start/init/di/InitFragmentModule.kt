package de.parndt.timetable.start.init.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.start.init.ui.InitFragment
import de.parndt.timetable.start.init.ui.InitViewModel
import de.parndt.timetable.start.simple.ui.StartFragment
import de.parndt.timetable.start.simple.ui.StartViewModel


@Module
class InitFragmentModule {
    @Provides
    fun initFragmentModuleViewModel(
        target: InitFragment,
        factory: InitViewModel.Factory
    ): InitViewModel =
        ViewModelProvider(target, factory)[InitViewModel::class.java]
}