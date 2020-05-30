package com.example.addnotes

//define a change info class by declaring parameter and their type
class ChangeInfo(val changeId:String, val title:String)
// this class doesn't need a body we don't include the curly braces

//define the NoteInfo class by dec
class NoteInfo(var change:ChangeInfo, var title: String,var text:String)