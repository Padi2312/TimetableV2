package de.parndt.timetable.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.AndroidInjection
import de.parndt.timetable.R
import de.parndt.timetable.general.Config
import de.parndt.timetable.general.RunType
import de.parndt.timetable.start.init.ui.InitFragment
import de.parndt.timetable.timetable.ui.TimetableFragment
import de.parndt.timetable.utils.NetworkConnection
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
        navigateToFragment(InitFragment())
    }


    /**
     * Navigates to a fragment
     * @param fragment Destination fragment where gets navigated to
     */
    fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.drawerLayout, fragment)
        fragmentTransaction.commit()
    }

}