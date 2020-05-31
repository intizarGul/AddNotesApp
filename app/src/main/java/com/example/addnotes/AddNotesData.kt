package com.example.addnotes

//define a change info class by declaring parameter and their type
class CourseInfo(val courseId:String, val title:String) {
	override fun toString(): String {
		return title
	}
}

//define the NoteInfo class by dec
class NoteInfo(var course:CourseInfo, var title: String, var text:String)