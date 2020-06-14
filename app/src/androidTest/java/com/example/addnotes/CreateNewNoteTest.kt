package com.example.addnotes

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest{
	//noteListActivity test
	//we launch the test with noteListActivity
	@Rule @JvmField
	val	noteListActivity = ActivityTestRule(NoteListActivity::class.java)
	
	@Test
	fun createNewNote(){
		// testing creating new note
		val noteTitle = "Test note title"
		val noteText = "This is the body of the test note"
		
		//testing when action button to add note is pressed we call the MainActivity
		onView(withId(R.id.addNotes)).perform(click())
		//once we are in the MainActivity we test for the fields below
		onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
		onView(withId(R.id.textNoteText)).perform(typeText(noteText))
	
	}
}