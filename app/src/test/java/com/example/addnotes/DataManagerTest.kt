package com.example.addnotes

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DataManagerTest {
	@Before
	fun setUp() {
		//We always start the note collections with same state
		//clear the DataManager notes collection
		DataManager.notes.clear()
		//add notes to use before we run the test itself
		DataManager.initializeNotes()
	}
	
	@Test
	fun addNote() {
		val course = DataManager.courses.get("android_async")!!
		val noteTitle = "This is a test"
		val noteText = "This is the text body of my test note"
		// the above are variable to create a new note
		
		// now in this section we need to call the newly created addNote function which has return type of INT
		
		val index = DataManager.addNote(course,noteTitle,noteText)
		//return the note added at that index
		val note = DataManager.notes[index]
		
		//testing the values in the note added
		//we write the test to check if added values matched with values in
		// notesList from the datamanager class
		assertEquals(course,note.course)
		assertEquals(noteTitle,note.title)
		assertEquals(noteText,note.text)
	}
	@Test
	fun findSimilarNotes(){
		val course = DataManager.courses.get("android_async")!!
		val noteTitle = "This is a test"
		val noteText1 = "This is the text body of my test note"
		val noteText2 = "This is the body of my second test note"
		
		//Add the notes to the list
		val index1 = DataManager.addNote(course,noteTitle,noteText1)
		val index2 = DataManager.addNote(course,noteTitle,noteText2)
		
		//after the note is added we have the note index
		val note1 = DataManager.findNote(course,noteTitle,noteText1)
		//find the index1 of note added
		val foundIndex1 = DataManager.notes.indexOf(note1)
		//now the the index added is matched with the foundIndex
		assertEquals(index1,foundIndex1)
		
		
		val note2 = DataManager.findNote(course,noteTitle,noteText2)
		val foundIndex2 = DataManager.notes.indexOf(note2)
		assertEquals(index2,foundIndex2)
	}
}