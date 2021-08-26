package com.example.todo_list.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_list.Model
import com.example.todo_list.NoteDatabase
import com.example.todo_list.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application:Application):AndroidViewModel(application) {
   val readAllData: LiveData<List<Model>>
    val repository:NoteRepository


    init {
        val noteDao=NoteDatabase.getDatabase(application).noteDao()
        repository= NoteRepository(noteDao)
        readAllData=repository.readAllNotes

    }

    fun addUser(note:Model){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)

        }

    }
    fun deleteNote(note:Model){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }
    fun updateNote(model:Model){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(model)
        }

    }




}