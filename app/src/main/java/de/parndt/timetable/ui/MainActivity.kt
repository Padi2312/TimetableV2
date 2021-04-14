package de.parndt.timetable.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import de.parndt.timetable.R
import de.parndt.timetable.general.Config
import de.parndt.timetable.general.Run
import de.parndt.timetable.start.ui.StartFragment
import de.parndt.timetable.timetable.ui.TimetableFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var config: Config

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        init()
    }

    private fun init() {
        when (config.isFirstRun()) {
            Run.FIRST_START -> {
                navigateToFragment(StartFragment())
            }
            Run.UPGRADE,
            Run.NORMAL -> {
                navigateToFragment(TimetableFragment())
            }
        }
    }

    fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.drawerLayout, fragment)
        fragmentTransaction.commit()
    }

}