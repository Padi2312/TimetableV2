package de.parndt.timetable.timetable.ui

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
import de.parndt.timetable.general.lecture.LectureDay
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
            showLectures(it)
        }

        timetableRefreshLayout.setOnRefreshListener {
            refresh()
        }

        viewModel.loadLectures()
    }

    private fun showLectures(lectures: List<LectureDay>) {
        hideLoadingIndicators()
        adapter.submitList(lectures)
        scrollToCurrentDay()
    }

    private fun refresh() {
        showLoadingIndicator()
        viewModel.refreshLectures()
    }

    private fun showLoadingIndicator() {
        timetableLoadingIndicator.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicators() {
        timetableRefreshLayout.isRefreshing = false
        timetableLoadingIndicator.visibility = View.GONE
    }

    private fun scrollToCurrentDay() {
        val positon = adapter.getPositionOfItemByDate(viewModel.getCurrentDate())
        lecturesList?.scrollToPosition(positon)
    }

}