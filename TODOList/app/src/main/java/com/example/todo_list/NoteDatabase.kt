package com.example.todo_list

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Model::class],version = 1,exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
        abstract fun noteDao():NoteDAO
        companion object{
            @Volatile
            private var INSTANCE:NoteDatabase?=null

            fun getDatabase(context:Context):NoteDatabase{
                val temp= INSTANCE
                if(temp!=null){
                    return temp
                }
                synchronized(this){
                    var instance= Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java,"all_notes_tables").build()
                    INSTANCE=instance
                    return instance
                }

            }

        }

}