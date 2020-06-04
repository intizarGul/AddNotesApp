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
    
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    
        spinnerCourse.adapter = adapterCourses
    
        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)
    
        if(notePosition != POSITION_NOT_SET)
            displayNote()
    }
    
    private fun displayNote() {
        // this function handles displaying notes
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)
        
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
            R.id.action_Next -> {
                moveNext()
                true
            }
            R.id.action_Previous ->{
                moveBack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun moveBack() {
        //move from one note to another move between items
        // to move to previous item we will decrease the noteposition that we worked out above
        --notePosition
        // now call the displayNote function
        displayNote()
    }
    
    private fun moveNext() {
        //move from one note to another move between items
        // to move to next item we will increase the noteposition that we worked out above
        ++notePosition
        // now call the displayNote function
        displayNote()
    }
}