package com.example.addnotes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*

@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class MainActivity : AppCompatActivity() {
    //Property to find the position of Note
    private var notePosition = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        
        //we are now using the adaptor class to populate the spinner with the information
        // from the DataManager Class
        
        //Create an instance of the adapter
        val adapterCourses = ArrayAdapter(this, // this activity as the context parameter
            android.R.layout.simple_spinner_item, // simple_spinner_item as the layout of the spinner
            DataManager.courses.values.toList()) //convert the courses values to a list
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Set another another layout to view the dropdown options
        
        //Connect the spinner dropdown to the adapterCourses variable
        spinnerCourse.adapter = adapterCourses
        //find the position of the note with the intent
        // if the position is set get the value if not return -1 position
        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        
        // display the note if position is set
        if (notePosition != POSITION_NOT_SET)
            displayNote()
    }
    
    private fun displayNote() {
        //find the note with it's position
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)
        
        //We need to get he course information from the Spinner in DataManager
        val coursePosition = DataManager.courses.values.indexOf<Any>(note.course)
        spinnerCourse.setSelection(coursePosition)
        
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}