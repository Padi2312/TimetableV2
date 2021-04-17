/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.start.initialstart.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.start.initialstart.ui.StartFragment
import de.parndt.timetable.start.initialstart.ui.StartViewModel


@Module
class StartFragmentModule {
    @Provides
    fun startFragmentModuleViewModel(
        target: StartFragment,
        factory: StartViewModel.Factory
    ): StartViewModel =
        ViewModelProvider(target, factory)[StartViewModel::class.java]
}