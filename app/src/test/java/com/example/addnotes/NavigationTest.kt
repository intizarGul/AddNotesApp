package com.example.addnotes

import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import androidx.test.espresso.Espresso.pressBack as espressoPressBack



@RunWith(AndroidJUnit4::class)
class NavigationTest {
	@Rule @JvmField
	val itemsActivity = ActivityTestRule(ItemsActivity::class.java)
	
	@Test
	fun selectNoteAfterNavigationDrawerChange(){
		//course selection test
		onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
		onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_home))
		
		val coursePosition = 0
		onView(withId(R.id.listCourses)).perform(
			RecyclerViewActions.actionOnItemAtPosition<CourseRecyclerAdapter.ViewHolder>(coursePosition,
				click())
		)
	
	}


}