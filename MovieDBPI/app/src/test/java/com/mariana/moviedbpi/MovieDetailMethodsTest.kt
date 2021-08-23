package com.mariana.moviedbpi

import com.mariana.moviedbpi.domain.entity.MovieDetail
import org.junit.Test
import org.junit.Assert.*

//Testes realizados para os métodos dispostos na classe MovieDetail

class MovieDetailMethodsTest {

    val movieDetailObject = MovieDetail(550, "/ohXr0v9U0TfFu9IXbSDm5zoGV3R.jpg", listOf(), "Clube da Luta", "1999-10-15", 8.4, 139, "Um homem deprimido que sofre de insônia conhece um estranho vendedor de sabonetes chamado Tyler Durden. Eles formam um clube clandestino com regras rígidas onde lutam com outros homens cansados de suas vidas mundanas. Mas sua parceria perfeita é comprometida quando Marla chama a atenção de Tyler.", false)

    @Test
    fun `returns a String with a number in percentage when a Double from 0 to 10 is inserted`(){
        val result = movieDetailObject.showUserRatingString()
        assertEquals("84%", result)
    }

    @Test
    fun `returns a String representing a year given the date on format YYYY-MM-dd`(){
        val result = movieDetailObject.showYearFromDate()
        assertEquals("1999", result)
    }

    @Test
    fun `returns a String in hours and minutes given a movie runtime of type Int in minutes`(){
        val result = movieDetailObject.showRuntimeInHoursAndMinutes()
        assertEquals("2h 19min", result)
    }
}