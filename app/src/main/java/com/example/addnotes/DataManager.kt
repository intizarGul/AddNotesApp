package com.example.addnotes

object DataManager {
    //We need to add properties to class
    //HashMap is a generic type they allow us to specify a specific type withing the type
    //HashMap accepts two type parameters
    //the first type will be used for lookups we will lookup our Changeinfo ID which is string
    // the second parameter type is a type that will be stored, which for our changes it's ChangeInfo
    
    val courses = HashMap<String, CourseInfo>()
    
    //We need another property to hold our collection of changes we use array for this purpose.
    val notes = ArrayList<NoteInfo>()
    
    init {
        // this code will run whenever and instance of the Datamanager is created
        intializeCourses()
        // initialise block accepts no parameter because there no way of running them explicitly
        // they are run automatically
        initializeNotes()
        
    }
    
    
    // We need to initialise our Change Info class we do this by creating a function with no parameters
// We make this method private to the DataManager class so it's not available from anywhere else
    private fun intializeCourses() {
        
        //for the HashMap lookup we do that by using it set method we pass the Id as first parameter
        // and the change var as the second parameter
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseId,course)
        //Adding a few more items ot the list
        // using the Name specific Parameters the order of parameters in which they are used doesn't matter
        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId,course)
        //using positional parameters
        course = CourseInfo("Notts_report_c365","Notts Waste Audit Dashboard report")
        courses.set(course.courseId,course)
        course = CourseInfo("report_c365", "Thirteen Group FRA Activity Dashboard")
        courses.set(course.courseId,course)
        course = CourseInfo("android_async", "Android Async Programming and Services")
        // the function above initializes the changeinfo class with five items
        courses.set(course.courseId,course)
    }
    
    private  fun initializeNotes () {
        var notes = NoteInfo("Android Programming with Intents","this is a text","this is a text")
        notes = NoteInfo("Notts_report_c365","Notts Waste Audit Dashboard report","a text")
        
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
}