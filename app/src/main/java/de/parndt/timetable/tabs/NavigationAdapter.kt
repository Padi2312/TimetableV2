/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import de.parndt.timetable.timetable.daytimetable.ui.DayTimetableFragment
import de.parndt.timetable.timetable.monthtimetable.ui.TimetableFragment
import de.parndt.timetable.utils.Logger

class NavigationAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                DayTimetableFragment()
            }
            1 -> {
                TimetableFragment()
            }
            else -> {
                Logger.error("No fragment found for position <$position>.")
                DayTimetableFragment()
            }
        }
    }
}