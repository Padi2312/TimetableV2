/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.parndt.timetable.start.init.di.InitFragmentModule
import de.parndt.timetable.start.init.ui.InitFragment
import de.parndt.timetable.start.initialstart.di.StartFragmentModule
import de.parndt.timetable.start.initialstart.ui.StartFragment
import de.parndt.timetable.timetable.di.TimetableFragmentModule
import de.parndt.timetable.timetable.ui.TimetableFragment
import de.parndt.timetable.ui.MainActivity


@Module
@Suppress("unused")
abstract class PlatformTypesInjectorModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [TimetableFragmentModule::class])
    abstract fun contributeTimetableFragment(): TimetableFragment

    @ContributesAndroidInjector(modules = [StartFragmentModule::class])
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector(modules = [InitFragmentModule::class])
    abstract fun contributeInitFragment(): InitFragment
}

