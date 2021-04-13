package de.parndt.Timetable.timetable.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import de.parndt.Timetable.R
import kotlinx.android.synthetic.main.fragment_timetable.*
import javax.inject.Inject

class TimetableFragment : Fragment() {

    @Inject
    lateinit var viewModel: TimetableViewModel
    private lateinit var adapter: TimetableListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TimetableListAdapter(requireContext())
        lecturesList.adapter = adapter
        lecturesList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        viewModel.getLectures().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            scrollToCurrentDay()
        }

        viewModel.loadLectures()
    }

    private fun scrollToCurrentDay() {
        val positon = adapter.getPositionOfItemByDate(viewModel.getCurrentDate())
        lecturesList?.scrollToPosition(positon)
    }

}