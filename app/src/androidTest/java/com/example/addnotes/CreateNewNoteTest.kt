package com.example.addnotes

import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
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
class CreateNewNoteTest{
	@Rule @JvmField
	val noteListActivity = ActivityTestRule(NoteListActivity::class.java)
	
	@Test
	fun createNewNote() {
		val course = DataManager.courses["android_async"]
		val noteTitle = "Test note title"
		val noteText = "This is the body of our test note"
		
		onView(withId(R.id.addNotes)).perform(click())
		
		onView(withId(R.id.spinnerCourse)).perform(click())
		onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())
		
		
		onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
		onView(withId(R.id.textNoteText)).perform(typeText(noteText), closeSoftKeyboard())
		
		pressBack()
		
		val newNote = DataManager.notes.last()
		assertEquals(course, newNote.course)
		assertEquals(noteTitle, newNote.title)
		assertEquals(noteText, newNote.text)
		assertEquals(null, "");
		
		
	}
}