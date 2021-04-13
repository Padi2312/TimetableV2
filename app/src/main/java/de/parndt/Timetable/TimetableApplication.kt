package de.parndt.Timetable

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector
import de.parndt.Timetable.di.ApplicationComponent
import de.parndt.Timetable.di.DaggerApplicationComponent

class TimetableApplication : DaggerApplication(), HasAndroidInjector {

    lateinit var applicationComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent =
            DaggerApplicationComponent.builder()
                .networkModule("").context(this).build()
        return applicationComponent
    }

}