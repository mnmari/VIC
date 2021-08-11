package com.mariana.moviedbpi

import org.junit.Test
import org.junit.Assert.*

//Testes realizados para os métodos dispostos na classe MovieDetail

class MovieDetailMethodsTest {
    @Test
    fun `returns a String with a number in percentage when a Double from 0 to 10 is inserted`(){
        val result = showUserRatingString(4.2)
        assertEquals("42%", result)
    }

    @Test
    fun `returns a String representing a year given the date on format YYYY-MM-dd`(){
        val result = showYearFromDate("2020-11-20")
        assertEquals("2020", result)
    }

    @Test
    fun `returns a String in hours and minutes given a movie runtime of type Int in minutes`(){
        val result = showRuntimeInHoursAndMinutes(140)
        assertEquals("2h 20min", result)
    }


    fun showUserRatingString(userRating: Double) : String {
        return "${"%.0f".format((userRating * 10.0))}%"
    }

    fun showYearFromDate(releaseDate: String) : String {
        return releaseDate.take(4)
    }

    fun showRuntimeInHoursAndMinutes(runtime: Int) : String {
        val hours = runtime/60
        val minutes = runtime%60

        return "%dh %dmin".format(hours, minutes)
    }


}