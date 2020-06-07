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
			val intentActivity = Intent(this,MainActivity::class.java)
			startActivity(intentActivity)
		}
		//set the NoteList Adaptor to list the items by using the R layout Class
		NoteList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)
		
		//When a Note is clicked then open it in the MainActivity Class
		NoteList.setOnItemClickListener { parent, view, postion, id ->
			val activityIntent = Intent(this,MainActivity::class.java)
			
			activityIntent.putExtra(EXTRA_NOTE_POSITION,postion)
			startActivity(activityIntent)
		
		}
	}
}