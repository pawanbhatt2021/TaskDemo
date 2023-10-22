package com.example.taskdemo.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskdemo.model.Form


@Dao
interface FormDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Form)
    //get All Notes
    @Query("SELECT * FROM Form")
    fun getAllNotes(): LiveData<List<Form>>

}