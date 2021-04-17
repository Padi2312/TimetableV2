/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.AndroidInjection
import de.parndt.timetable.R
import de.parndt.timetable.general.Config
import de.parndt.timetable.start.init.ui.InitFragment
import de.parndt.timetable.start.initialstart.ui.StartFragment
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Kurs ändern")
                    .setMessage("Wollen sie ihren Kurs ändern")
                    .setPositiveButton(resources.getString(android.R.string.yes)) { _, _ ->
                        navigateToFragment(StartFragment())
                    }
                    .setNegativeButton(resources.getString(android.R.string.no)) { _, _ ->

                    }
                    .show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

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