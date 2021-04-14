package de.parndt.timetable.general

import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.exceptions.CsvException
import de.parndt.timetable.utils.Logger
import java.io.StringReader

object CSVParser {

    /**
     * Parses a CSV string to a list of the given type
     * @param csvString String of the CSV-File
     * @param separator Separator, default value is ','
     * @return List of given type with the parsed rows
     */
    inline fun <reified T> parseToFromString(csvString: String, separator: Char = ','): List<T> {

        try {
            return CsvToBeanBuilder<T>(StringReader(csvString))
                .withType(T::class.java)
                .withSeparator(separator).build().parse()
        } catch (e: CsvException) {
            Logger.error("Error parsing CSV String to Class")
            throw e
        }
    }

}