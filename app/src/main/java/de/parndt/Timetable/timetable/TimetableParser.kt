package de.parndt.Timetable.timetable

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimetableParser @Inject constructor() {

    private val raplaLink: String =
        "https://rapla.dhbw.de/rapla/calendar?key=25q8zGuMAw3elezlMsiegXs3Z-sCY45qHbigy7wiQ2cWqtfFpWuLAvVQd_iN0dKYhutdO1Xd3fTJsseyRHWBtULWSiwTkukLYIf_kLWMdouHD-0trPgsEm_kH_dUA4Ut9NGhiU-J-PcjKAu0N04khc960zqtLYA8pdi87Y5CIBf-nZHBs65QFvlWbydaYESj&salt=-2070726140&today=Heute"
    private var timeTable: Document? = null
        get() {
            if (field == null) {
                loadTimetableDocument()
            }
            return field!!
        }

    private fun loadTimetableDocument() {
        timeTable = Jsoup.connect(raplaLink).get()
    }

    fun getLectures(): List<Lecture> {
        val weekBlocks = getWeekBlocks()
        val lectureList: MutableList<Lecture> = mutableListOf()
        weekBlocks.forEach { weekBlock ->

            val name = getName(weekBlock)
            val date = getDate(weekBlock)
            val time = getTime(weekBlock)

            lectureList.add(Lecture(name, date, time))
        }
        return lectureList
    }

    private fun getWeekBlocks(): Elements {
        return timeTable!!.select(".week_block")
    }

    private fun getName(weekBlock: Element): String {
        val tooltip = getTooltipElement(weekBlock)
        return tooltip.selectFirst(".value").text()
    }

    private fun getDate(weekBlock: Element): String {
        val tooltip = getTooltipElement(weekBlock)
        val dateTime = getDateTimeElement(tooltip)
        return dateTime.subSequence(3, 11).toString()
    }

    private fun getTime(weekBlock: Element): String {
        val tooltip = getTooltipElement(weekBlock)
        val dateTime = getDateTimeElement(tooltip)
        return dateTime.subSequence(12, dateTime.length).toString()
    }


    private fun getDateTimeElement(tooltip: Element): String {
        return tooltip.children()[1].text()
    }

    private fun getTooltipElement(element: Element): Element {
        return element.selectFirst(".tooltip")
    }
}