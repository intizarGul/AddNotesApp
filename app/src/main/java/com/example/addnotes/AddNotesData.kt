package com.example.addnotes

data class CourseInfo (val courseId: String, val title: String) {
	override fun toString(): String {
		return title
	}
}

// to add a new note we made each of the properties to be nullable
// this would allow us to create a new instance of the NoteInfo class and pass null for each of the properties
data class NoteInfo(var course: CourseInfo? = null, var title: String? = null, var text: String? = null)