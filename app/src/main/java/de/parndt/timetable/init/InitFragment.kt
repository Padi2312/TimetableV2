/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.init

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.AndroidSupportInjection
import de.parndt.timetable.MainActivity
import de.parndt.timetable.R
import de.parndt.timetable.firststart.StartFragment
import de.parndt.timetable.general.RunType.*
import de.parndt.timetable.tabs.TabsFragment
import javax.inject.Inject

class InitFragment : Fragment() {

    @Inject
    lateinit var viewModel: InitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_init, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getInitState().observe(viewLifecycleOwner) { initState ->
            if (initState == EInitState.FIRST_INIT) {
                (requireActivity() as MainActivity).navigateToFragment(StartFragment())
            } else {
                //(requireActivity() as MainActivity).navigateToFragment(TimetableFragment())
                (requireActivity() as MainActivity).navigateToFragment(TabsFragment())
            }
        }

        when (viewModel.getAppRunType()) {
            FIRST_START -> {
                firstStart()
            }
            UPGRADE,
            NORMAL -> {
                viewModel.loadLectures()
            }
        }
    }


    private fun firstStart() {
        if (!viewModel.checkNetworkConnectionOnFirstStart()) {
            noNetworkConnection()
        } else {
            viewModel.loadCourses()
        }
    }


    private fun noNetworkConnection() {
        MaterialAlertDialogBuilder(requireContext())
            .setCancelable(false)
            .setTitle("Keine Internet-Verbindung")
            .setMessage("F??r den ersten Start wird eine Internet-Verbindung ben??tigt. Stellen sie diese bitte her und versuchen sie es erneut. Die App wird nun geschlossen. ")
            .setNeutralButton(resources.getString(android.R.string.ok)) { _, _ ->
                requireActivity().finish()
            }
            .setOnDismissListener {
                viewModel.cancelInit()
                requireActivity().finish()
            }
            .show()
    }
}