/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import de.parndt.timetable.R
import de.parndt.timetable.utils.Logger
import kotlinx.android.synthetic.main.fragment_tabs.*

class TabsFragment :Fragment() {

    private lateinit var adapter: NavigationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NavigationAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tab_layout,viewPager){ tab, position ->

            tab.text = when (position) {
                0 -> {
                    "Heute"
                }
                1 -> {
                    "Stundenplan"
                }
                else -> {
                    Logger.error("No tab found for position <$position>.")
                    "Heute"
                }
            }

        }.attach()
    }

}