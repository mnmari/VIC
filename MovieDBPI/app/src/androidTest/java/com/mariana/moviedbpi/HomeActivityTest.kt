package com.mariana.moviedbpi

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mariana.moviedbpi.presentation.HomeActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun launch_home_screen() {
        onView(withId(R.id.tblMenuHomeActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun switch_to_search_movies_fragment(){
        onView(withId(R.id.edtSearchMovieHomeActivity)).perform(click()).perform(typeText("Toy Story")).perform(
            pressImeActionButton())
    }
}