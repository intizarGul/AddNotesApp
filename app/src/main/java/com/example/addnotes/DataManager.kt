package com.example.addnotes

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.addnotes.NotesCollectionDBContract.CourseInfoEntry
import com.example.addnotes.NotesCollectionDBContract.NoteInfoEntry


object DataManager {
    private var ourInstance: DataManager? = null
    val courses = ArrayList<CourseInfo>()
    val notes = ArrayList<NoteInfo>()
    
    fun getInstance(): DataManager? {
        if (ourInstance == null) {
            ourInstance = DataManager
            //            ourInstance.initializeCourses();
//            ourInstance.initializeExampleNotes();
        }
        return ourInstance
    }
    
    fun loadFromDatabase(dbHelper: NotesCollectionDatabaseHelper) {
        val db: SQLiteDatabase = dbHelper.readableDatabase
    
        val columns = arrayOf(
            CourseInfoEntry.COLUMN_COURSE_ID,
            CourseInfoEntry.COLUMN_TITLE)
        
        val cursor =  db.query(CourseInfoEntry.TABLE_NAME,
            columns,null,null,null,null,CourseInfoEntry.COLUMN_TITLE.toString() + " DESC")
        //fetchAllCourses(cursor)
        
        
        val noteColumns = arrayOf(
            NoteInfoEntry.COLUMN_TITLE,
            NoteInfoEntry.COLUMN_TEXT,
            NoteInfoEntry.COLUMN_COURSE_ID
        )
        val noteOrderBy =
            NoteInfoEntry.COLUMN_COURSE_ID + "," + NoteInfoEntry.COLUMN_TITLE
        val noteCursor: Cursor = db.query(
            NoteInfoEntry.TABLE_NAME, noteColumns,
            null, null, null, null, noteOrderBy
        )
        loadNotesFromDatabase(noteCursor)
    }
    
    
    
    
    fun fetchAllCourses(databaseHelper: NotesCollectionDatabaseHelper) : ArrayList<CourseInfo>{
    
        val courses = ArrayList<CourseInfo>()
        
        val db: SQLiteDatabase = databaseHelper.readableDatabase
        val columns = arrayOf(
            CourseInfoEntry.COLUMN_COURSE_ID,
            CourseInfoEntry.COLUMN_TITLE)
    
        val cursor =  db.query(CourseInfoEntry.TABLE_NAME,
            columns,null,null,null,null,CourseInfoEntry.COLUMN_TITLE.toString() + " DESC")
        
        //we begin the cursor by getting the column position
        val cIdPos = cursor.getColumnIndex(CourseInfoEntry.COLUMN_COURSE_ID)
        val cTitlePos = cursor.getColumnIndex(CourseInfoEntry.COLUMN_TITLE)
    
        //cursor by default is at position -1 move this to first position which is 0
        // keep doing this until we reach the end of the results by using a while loop
        val dm: DataManager? = getInstance()
        dm?.courses?.clear();
        while (cursor.moveToNext()) {
        
            // we have the position
            val courseId = cursor.getString(cIdPos)
            val courseTitle = cursor.getString(cTitlePos)
            val course = CourseInfo(courseId,courseTitle)
            //val surgeon = cursor.getInt(surgeonPos)
            //add this to the coursesArray
            courses.add(course)
        }
    
        //cursor closed here to release the resources
        cursor.close()
        
        return courses
        
    }
    
    
   /* fun fetchAllCourses(cursor: NotesCollectionDatabaseHelper){
        //we begin the cursor by getting the column position
        val cIdPos = cursor.getColumnIndex(CourseInfoEntry.COLUMN_COURSE_ID)
        val cTitlePos = cursor.getColumnIndex(CourseInfoEntry.COLUMN_TITLE)
        
        //cursor by default is at position -1 move this to first position which is 0
        // keep doing this until we reach the end of the results by using a while loop
        val dm: DataManager? = getInstance()
        dm?.courses?.clear();
        while (cursor.moveToNext()) {
            
            // we have the position
            val courseId = cursor.getString(cIdPos)
            val courseTitle = cursor.getString(cTitlePos)
            val course = CourseInfo(courseId,courseTitle)
            //val surgeon = cursor.getInt(surgeonPos)
            //add this to the coursesArray
            courses.add(course)
        }
        
        //cursor closed here to release the resources
        cursor.close()
        
    }*/
    fun loadNotesFromDatabase(cursor: Cursor) {
        val noteTitlePos = cursor.getColumnIndex(NoteInfoEntry.COLUMN_TITLE)
        val noteTextPos = cursor.getColumnIndex(NoteInfoEntry.COLUMN_TEXT)
        val courseIdPos = cursor.getColumnIndex(NoteInfoEntry.COLUMN_COURSE_ID)
    
        val dm = getInstance()
        dm?.notes?.clear()
        while (cursor.moveToNext()){
            val noteTitle = cursor.getString(noteTitlePos)
            val noteText = cursor.getString(noteTextPos)
            val courseId = cursor.getString(courseIdPos)
            
            val noteCourse:CourseInfo= dm?.getCourse(courseId)!!
            val note = NoteInfo(noteCourse,noteTitle,noteText)
            notes.add(note)
        }
        cursor.close()
    }
    //adding new note function following the test driven approach of development
    fun addNote(course:CourseInfo,noteTitle:String,noteText:String):Int{
        //the function above takes 3 Parameters and returns an INT values
        // to add a note we need to get an instance of the NoteInfo Class
        val note =NoteInfo(course,noteTitle,noteText)
        //once the note is created add this to the list
        notes.add(note)
        // We will now return the last added course by accessing the lastIndex of the notes list
        return notes.lastIndex
    }
    
    fun findNote(course: CourseInfo,noteTitle: String,noteText: String) : NoteInfo? {
        //find notes to make consistency of test
        for (note in notes)
            if (course == note.course &&
                    noteTitle == note.title &&
                    noteText == note.text)
                return note
        return null
    }
    
    fun getCurrentUserName(): String? {
        return "Wali Aubi"
    }
    fun getCurrentUserEmail(): String? {
        return "intikhn5@gmail.com"
    }
    fun getNotes(): List<NoteInfo?>? {
        return notes
    }
    fun createNewNote(): Int {
        val note = NoteInfo(null, null, null)
        notes.add(note)
        return notes.size - 1
    }
    fun findNote(note: NoteInfo): Int {
        for (index in 0 until this.notes.size) {
            if (note == notes[index]) return index
        }
        return -1
    }
    
    
    fun removeNote(index: Int) {
        notes.removeAt(index)
    }
    
    fun getCourses(): List<CourseInfo?>? {
        return courses
    }
    
    fun getCourse(id: String): CourseInfo? {
        for (course in courses) {
            if (id == course.courseId) return course
        }
        return null
    }
    
    fun getNotes(course: CourseInfo): List<NoteInfo>? {
        val notes: ArrayList<NoteInfo> = ArrayList()
        for (note in notes) {
            if (course == note.course) notes.add(note)
        }
        return notes
    }
    
    
    fun getNoteCount(course: CourseInfo): Int {
        var count = 0
        for ((course1) in notes) {
            if (course == course1) count++
        }
        return count
    }
    
    private fun DataManager() {}
    fun createNewNote(
        course: CourseInfo?,
        noteTitle: String?,
        noteText: String?
    ): Int {
        val index = createNewNote()
        val note: NoteInfo = getNotes()?.get(index)!!
        note.course = course
        note.title = noteTitle
        note.text = noteText
        return index
    }
}