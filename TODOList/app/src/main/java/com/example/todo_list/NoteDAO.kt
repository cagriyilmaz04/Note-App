package com.example.todo_list

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addNote(note:Model)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
   fun readAllData(): LiveData<List<Model>>

   @Delete
   suspend fun deleteNote(note:Model)

   @Query("DELETE FROM note_table")
   suspend fun deleteAllNotes()

   @Update
   suspend fun updateNote(note:Model)

}