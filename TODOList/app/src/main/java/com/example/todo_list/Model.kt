package com.example.todo_list

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class Model(
    var baslik:String, var metin:String, var olusturulma_tarihi:String,val flag:Int,
    @PrimaryKey(autoGenerate = true)
                 val id:Int):Parcelable