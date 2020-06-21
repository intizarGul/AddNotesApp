package com.example.addnotes

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_note.*
import kotlin.collections.indexOf as collectionsIndexOf

@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class NoteActivity : AppCompatActivity() {
    //creating a property to log entries by class name
    private  val tag = this::class.simpleName
    //Property to find the position of Note
    private var notePosition = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(findViewById(R.id.toolbar))
        
        //we are now using the adaptor class to populate the spinner with the information
        // from the DataManager Class
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    
        spinnerCourse.adapter = adapterCourses
        
        //We first check savedInstanceState if it's non-null then we go ahead and get the
        // saved note position from the instance state but if savedInstanceState is null
        // that would result in a null value and so then the Elvis operator will then cause us
        // to get the note position from the extra
        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET)?:
            intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)
        
        //The statement below will tell us if we have a note if yes then we display the Note
        if(notePosition != POSITION_NOT_SET)
            displayNote()
        //if then we create a new Note
        else{
            createNewNote()
        }
        // calling a debug log on entry when the on create method is ran
        Log.d(tag,"onCreate")
    }
    
    private fun createNewNote() {
        //create a new instance of the NoteInfo class and add it to DataManager notes collection
        // and the set the position of the MainActivity to last index
        DataManager.notes.add(NoteInfo())
        notePosition = DataManager.notes.lastIndex
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //saving the instance of the MainActivity  by getting the noteposition
        //because the noteposition is an int we will call the putInt property of the outStateParameter
        outState.putInt(NOTE_POSITION,notePosition)
    }
    
    private fun displayNote() {
        if (notePosition > DataManager.notes.lastIndex){
            showToast("Note not found",Toast.LENGTH_SHORT)
            //error log
            Log.e(tag,"Invalid note position $notePosition, max valid position ${DataManager.notes.lastIndex}")
        }
        
        // this function handles displaying notes state when
        // log noteposition information
        Log.i(tag,"Displaying note for position $notePosition")
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)
        
        val coursePosition = DataManager.courses.values.collectionsIndexOf(note.course)
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
        notePosition -= 1
        // now call the displayNote function
        displayNote()
        // the way we schedule a call to onPrepareOptionsMenu() method
        // is by calling the invalidateOptionsMenu()
        invalidateOptionsMenu()
    }
    
    private fun moveNext() {
        //move from one note to another move between items
        // to move to next item we will increase the noteposition that we worked out above
        
        ++notePosition
        // now call the displayNote function
        displayNote()
        // the way we schedule a call to onPrepareOptionsMenu() method
        // is by calling the invalidateOptionsMenu()
        invalidateOptionsMenu()
        
    }
    
    //override icon when users gets to the last item
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // We are checking when we get to the last index of the collection list then change the icon
        if(notePosition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_Next)
            if(menuItem != null)
                menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                menuItem?.isEnabled = false
                showToast("No more items in the list")
        } else {
            //The Button is disabled when the list gets to 0
            if (notePosition == 0) {
                val menuItem = menu?.findItem(R.id.action_Previous)
                if (menuItem != null)
                    menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                menuItem?.isEnabled = false
                showToast("Please click next to see your other notes",Toast.LENGTH_SHORT)
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }
    
    override fun onPause() {
        //save the note when the users leaves the activity override the onPause Method()
        // this will automatically happen when the activity is moved from the foreground
        super.onPause()
        saveNote()
        Log.d(tag,"onPause")
    }
    
    private fun saveNote() {
       //to save the note we need to get the position of the selected note
        val note = DataManager.notes[notePosition]
        //set the note title get the id for the note title view and set the text property
        //convert toString
        note.title = textNoteTitle.text.toString()
        //set the note text get the id for the note text view and set the text property
        //convert toString
        note.text = textNoteText.text.toString()
        //set the course get the id for the course and set the selectedItem property
        // when we select the course we have the reference to the selected course but not the courseInfo reference
        //cast the course as CourseInfo to get the courseInfo reference
        note.course = spinnerCourse.selectedItem as CourseInfo
    }
    private fun showToast(message: String,toastDuration: Int = Toast.LENGTH_LONG) {
        // Create and show the toast message
        val toast = Toast.makeText(this, message, toastDuration)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
