package com.example.todolist

data class NoteModel (val notes: List<Data>)
{
    data class Data (val id: String?, val note: String?, val title: String, var isChecked: Boolean = false)
}