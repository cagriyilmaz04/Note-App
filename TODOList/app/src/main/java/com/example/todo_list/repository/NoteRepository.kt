package com.example.todo_list.repository

import androidx.lifecycle.LiveData
import com.example.todo_list.Model
import com.example.todo_list.NoteDAO

class NoteRepository( val noteDAO: NoteDAO) {
    val readAllNotes:LiveData<List<Model>> = noteDAO.readAllData()
   suspend fun addNote(note:Model){
        noteDAO.addNote(note)
    }
    suspend fun deleteNote(note:Model){
        noteDAO.deleteNote(note)
    }
    suspend fun deleteAllNotes(){
        noteDAO.deleteAllNotes()
    }

    suspend fun updateNote(note:Model){
        noteDAO.updateNote(note)
    }



}