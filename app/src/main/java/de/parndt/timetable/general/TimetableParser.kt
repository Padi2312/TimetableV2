package de.parndt.timetable.general

import de.parndt.timetable.general.lecture.Lecture
import de.parndt.timetable.general.lecture.LectureCSVModel
import de.parndt.timetable.utils.Logger
import de.parndt.timetable.utils.Utils
import okhttp3.OkHttpClient
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimetableParser @Inject constructor(
    private val httpClient: OkHttpClient
) {

    var lectureList: List<Lecture> = listOf()
    val raplaCSVLink =
        "https://rapla.dhbw.de/rapla/calendar.csv?key=BX2MrCsUIl-Bm8MTaxjslJ5wzIryM3bkwiDCe4PaYyfZXI0yz1sCIhXdyNtXoZmA4dkOgdRA7EzUF6DPupMNP-fIrgQkDVd99rZKEbgqKhajWy7WGDsHbMqAunKlxm8EGryjvwt1kpad5g93Dkdn0A&salt=-668364712&allocatable_id="
    val raplaLink =
        "https://rapla.dhbw.de/rapla/calendar?key=BX2MrCsUIl-Bm8MTaxjslJ5wzIryM3bkwiDCe4PaYyfZXI0yz1sCIhXdyNtXoZmA4dkOgdRA7EzUF6DPupMNP-fIrgQkDVd99rZKEbgqKhajWy7WGDsHbMqAunKlxm8EGryjvwt1kpad5g93Dkdn0A&salt=-668364712"


    private fun loadTimetable(): List<LectureCSVModel>? {
        val request = Utils.reqeust(raplaCSVLink)
        httpClient.newCall(request).execute().use { response ->

            if (!response.isSuccessful) {
                Logger.error("Download Timetable request was not successful. Error: $response")
                return null
            }

            val test = response.body!!.string()
            return CSVParser.parseToFromString<LectureCSVModel>(test, ';')
        }
    }

    fun getLectures(): List<Lecture>? {
        if (lectureList.isNotEmpty()) {
            return lectureList
        }

        val timetable = loadTimetable() ?: return null
        val lecturesList = mutableListOf<Lecture>()

        timetable.forEach { lecture ->
            lecturesList.add(
                Lecture(
                    lecture.name ?: "",
                    lecture.date ?: "",
                    lecture.times ?: "",
                    lecture.course ?: "",
                    lecture.room ?: ""
                )
            )
        }

        return lecturesList
    }

    fun getCourses(): List<String> {
        val courseList = Jsoup.connect(raplaLink).get()
        val courseNameList = mutableListOf<String>()
        val courseTableBody = courseList!!.select("table")[1].children().first()

        courseTableBody.children().forEach { tr ->
            courseNameList.add(tr.children().first().text())
        }
        return courseNameList
    }

}