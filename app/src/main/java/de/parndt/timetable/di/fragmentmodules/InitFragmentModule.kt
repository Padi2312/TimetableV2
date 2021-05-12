/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di.fragmentmodules

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.parndt.timetable.init.InitFragment
import de.parndt.timetable.init.InitViewModel


@Module
class InitFragmentModule {
    @Provides
    fun initFragmentModuleViewModel(
        target: InitFragment,
        factory: InitViewModel.Factory
    ): InitViewModel =
        ViewModelProvider(target, factory)[InitViewModel::class.java]
}