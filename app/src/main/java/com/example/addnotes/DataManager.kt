package com.example.addnotes

import com.example.addnotes.NotesCollectionDBContract.CourseInfoEntry

object DataManager {
    fun fetchAllEmployees(databaseHelper: NotesCollectionDatabaseHelper) : ArrayList<CourseInfo> {
        
        //create an empty list of employees
        val employees = ArrayList<CourseInfo>()
        
        
        //open the connection to the database
        val db = databaseHelper.readableDatabase
        
        //Array of the columns
        val columns = arrayOf(
            CourseInfoEntry.COLUMN_ID,
            CourseInfoEntry.COLUMN_COURSE_ID,
            CourseInfoEntry.COLUMN_TITLE)
        
        
        
        //Query the columns and assign the query to a var
        val cursor =  db.query(CourseInfoEntry.TABLE_NAME,
            columns,null,null,null,null,null)
        
        
        //we begin the cursor by getting the column position
        val idPos = cursor.getColumnIndex(CourseInfoEntry.COLUMN_ID)
        val cIdPos = cursor.getColumnIndex(CourseInfoEntry.COLUMN_COURSE_ID)
        val cTitlePos = cursor.getColumnIndex(CourseInfoEntry.COLUMN_TITLE)
        
        //cursor by default is at position -1 move this to first position which is 0
        // keep doing this until we reach the end of the results by using a while loop
        
        while (cursor.moveToNext()) {
            
            // we have the position
            val id = cursor.getString(idPos)
            val courseId = cursor.getString(cIdPos)
            val courseTitle = cursor.getString(cTitlePos)
            //val surgeon = cursor.getInt(surgeonPos)
            
            //add this to the employee array
            employees.add(CourseInfo(id,courseId,courseTitle))
        }
        
        //cursor closed here to release the resources
        cursor.close()
        //return the employee array list
        return employees
        
    }
    
    
    //val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()
    
    init {
        //initializeCourses()
        //initializeNotes()
    }
    
    /*private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseId, course)
        
        course = CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseId, course)
        
        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId, course)
        
        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
        
        course = CourseInfo("kotlin_core", "Kotlin Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
    
        course = CourseInfo(title = "Kotlin Fundamentals: The Kotlin Language", courseId = "kotlin_lang")
        courses.set(course.courseId, course)
    }*/
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
    
    /*fun initializeNotes() {
        
        var course = courses["android_intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)
        
        course = courses["android_async"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)
        
        course = courses["java_lang"]!!
        note = NoteInfo(course, "Parameters",
            "Leverage variable-length parameter lists")
        notes.add(note)
        note = NoteInfo(course, "Anonymous classes",
            "Anonymous classes simplify implementing one-use types")
        notes.add(note)
        
        course = courses["java_core"]!!
        note = NoteInfo(course, "Compiler options",
            "The -jar option isn't compatible with with the -cp option")
        notes.add(note)
        note = NoteInfo(course, "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }*/
}