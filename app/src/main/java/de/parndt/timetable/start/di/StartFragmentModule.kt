package de.parndt.timetable.start.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.start.ui.StartFragment
import de.parndt.timetable.start.ui.StartViewModel


@Module
class StartFragmentModule {
    @Provides
    fun startFragmentModuleViewModel(
        target: StartFragment,
        factory: StartViewModel.Factory
    ): StartViewModel =
        ViewModelProvider(target, factory)[StartViewModel::class.java]
}