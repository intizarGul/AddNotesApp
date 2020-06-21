package com.example.addnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note_list.*
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager

class NoteListActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_note_list)
		
		//Add Notes Button on click listener
		addNotes.setOnClickListener{
			val intentActivity = Intent(this,NoteActivity::class.java)
			startActivity(intentActivity)
		}
		
		//associate layout manager with RecyclerView
		ListItems.layoutManager = LinearLayoutManager(this)
		ListItems.adapter = NoteRecyclerAdapter(this,DataManager.notes)
		
		
	}
	
	override fun onResume() {
		super.onResume()
		//notify the Adapter to display the note changes
		ListItems.adapter?.notifyDataSetChanged()
	}
}