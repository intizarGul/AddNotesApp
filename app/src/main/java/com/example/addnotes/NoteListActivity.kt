package com.example.addnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note_list.*
import android.widget.ArrayAdapter

class NoteListActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_note_list)
		
		//Add Notes Button on click listener
		addNotes.setOnClickListener{
			val intentActivity = Intent(this,NoteActivity::class.java)
			startActivity(intentActivity)
		}
		
		
		
		
	}
	
	//When a new note is added to the arrayAdapter the size of the array will change this will result in app crashing
	//the new note is not showing yet to show the note we need to override the onResume method
	//this means when the NoteListActivity is in foreground show the new items added
	override fun onResume() {
		super.onResume()
		
	}
}