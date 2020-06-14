package com.example.addnotes

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
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
	//noteListActivity test
	//we launch the test with noteListActivity
	@Rule @JvmField
	val	noteListActivity = ActivityTestRule(NoteListActivity::class.java)
	
	@Test
	fun createNewNote(){
		// testing creating new note we are creating the variables below and initialise with some values
		val course = DataManager.courses["android_async"]
		val noteTitle = "Test note title"
		val noteText = "This is the body of the test note"
		
		//testing when action button to add note is pressed we call the MainActivity using the onView method
		onView(withId(R.id.addNotes)).perform(click())
		//once we are in the MainActivity we test for the fields below
		onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
		onView(withId(R.id.textNoteText)).perform(typeText(noteText),closeSoftKeyboard())
		
		//creating when we click on the spinner before selecting the data using the onView Method
		// this idea of clicking before making the selection is only specific to spinners
		onView(withId(R.id.spinnerCourse)).perform(click())
		//making selection from adapter view spinner data using the onData Method
		onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())
		
		
		
		//testing for the back button by using the press back Method
		espressoPressBack()
		
		
		// testing to make sure the UI behaves as expected using the assert class
		//when a note is added to notes collection in Datamanager get the last added note
		//validate the course information
		val newNote = DataManager.notes.last()
		assertEquals(course,newNote.course)
		assertEquals(noteTitle,newNote.title)
		assertEquals(noteText,newNote.text)
		
	
	}
}