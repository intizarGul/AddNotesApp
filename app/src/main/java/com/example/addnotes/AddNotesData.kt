package com.example.addnotes

//define a change info class by declaring parameter and their type
data class CourseInfo(val courseId:String, val title:String) {
	override fun toString(): String {
		return title
	}
}

//define the NoteInfo class as data class
data class NoteInfo(var course: String, var title: String, var text:String)