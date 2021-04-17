/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.start.initialstart.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import de.parndt.timetable.R
import de.parndt.timetable.timetable.ui.TimetableFragment
import de.parndt.timetable.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

class StartFragment : Fragment() {

    @Inject
    lateinit var viewModel: StartViewModel
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getCourseNames().observe(viewLifecycleOwner) {

            adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                it
            )

            startCourseList.adapter = adapter
        }

        viewModel.loadCourseNames()

        onItemSelected()
        setOnClickNext()
    }

    private fun onItemSelected() {
        startCourseList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val courseName = parent?.getItemAtPosition(position).toString()
                if (courseName.isNotEmpty()) {
                    viewModel.setCourse(courseName) {
                        requireActivity().runOnUiThread {
                            enableNextButton()
                        }
                    }
                } else {
                    disableNextButton()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setOnClickNext() {
        startNextButton.setOnClickListener {
            if (viewModel.isCourseSet()) {
                (requireActivity() as MainActivity).navigateToFragment(TimetableFragment())
            }
        }
    }

    private fun enableNextButton() {
        startNextButton.isEnabled = true
    }

    private fun disableNextButton() {
        startNextButton.isEnabled = false
    }

}