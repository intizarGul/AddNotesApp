package com.example.addnotes

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigateThroughNotesTest{
	@Rule @JvmField
	val noteListActivity = ActivityTestRule(NoteListActivity::class.java)
	
	@Test
	fun navigateThroughNotes(){
		//get the first note from the NoteInfo Class and perform the click action inside the ListView
		onData(allOf(instanceOf(NoteInfo::class.java), equalTo(DataManager.notes[0]))).perform(click())
		//now we need to add a for loop to loop through all the items in the list
		for (index in 0..DataManager.notes.lastIndex) {
			//we need a reference to the note at that index
			val note = DataManager.notes[index]
			// the below code will verify the data at that index of the note
			
			onView(withId(R.id.spinnerCourse)).check(
					matches(withSpinnerText(note.course?.title)))
			
			//find the view of note title and check the values match the displayed title
			onView(withId(R.id.textNoteTitle)).check(
					matches(withText(note.title)))
			
			onView(withId(R.id.textNoteText)).check(
				matches(withText(note.text)))
			
			//test the next button option
			//check to see the item is not the last item in the last list
			if (index != DataManager.notes.lastIndex)
				onView(allOf(withId(R.id.action_Next), isEnabled())).perform(click())
			
			//test the previous button option
			//check to see the item is not the last item in the first list
			//if (index == 0)
				//onView(allOf(withId(R.id.action_Previous), isEnabled())).perform(click())
		}
		onView(withId(R.id.action_Next)).check(matches(isEnabled()))
		//onView(withId(R.id.action_Next)).check(matches(isEnabled()))
		onView(withId(R.id.action_Previous)).check(matches(isEnabled()))
	}
	
}