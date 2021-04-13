package de.parndt.Timetable.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.parndt.Timetable.timetable.di.TimetableFragmentModule
import de.parndt.Timetable.timetable.ui.TimetableFragment
import de.parndt.Timetable.ui.MainActivity


@Module
@Suppress("unused")
abstract class PlatformTypesInjectorModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [TimetableFragmentModule::class])
    abstract fun contributeLoginFragment(): TimetableFragment

}

