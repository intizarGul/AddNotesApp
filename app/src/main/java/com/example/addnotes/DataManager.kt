package com.example.addnotes

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()
    
    init {
        initializeCourses()
        initializeNotes()
    }
    
    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseId, course)
        
        course = CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseId, course)
        
        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId, course)
        
        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
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
    
    fun initializeNotes() {
        
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
    }
}