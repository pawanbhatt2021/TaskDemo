package com.example.taskdemo.repository

import androidx.lifecycle.LiveData
import com.example.taskdemo.Dao.FormDao

import com.example.taskdemo.model.Form

class FormRepository(private val dao: FormDao) {
    suspend fun insertNotes(notes: Form) = dao.insertNotes(notes)
    fun getAllForm(): LiveData<List<Form>> = dao.getAllNotes()
}