/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di.fragmentmodules

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.firststart.StartFragment
import de.parndt.timetable.firststart.StartViewModel


@Module
class StartFragmentModule {
    @Provides
    fun startFragmentModuleViewModel(
        target: StartFragment,
        factory: StartViewModel.Factory
    ): StartViewModel =
        ViewModelProvider(target, factory)[StartViewModel::class.java]
}