/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.daytimetable

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import de.parndt.timetable.R
import kotlinx.android.synthetic.main.fragment_day_timetable.*
import javax.inject.Inject

class DayTimetableFragment : Fragment() {

    @Inject
    lateinit var viewModel: DayTimetableViewModel

    private lateinit var adapter: DayTimetableListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_timetable, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getTodaysLectureDay().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.submitList(it.lecturesList)
                dayDate.text = it.Date
            }

        }

        adapter = DayTimetableListAdapter(requireContext())
        dayLectureList.adapter = adapter
        dayLectureList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }
}