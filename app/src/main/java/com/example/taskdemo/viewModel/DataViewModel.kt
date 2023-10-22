package com.example.taskdemo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taskdemo.Database.FormDatabase
import com.example.taskdemo.model.Form
import com.example.taskdemo.repository.FormRepository

class DataViewModel(application: Application): AndroidViewModel(application) {
    private val formRepository: FormRepository
    init {
        val dao= FormDatabase.getDatabaseInstance(application).getNotesDao()
        formRepository= FormRepository(dao)
    }
    suspend fun insertNote(form: Form) = formRepository.insertNotes(form)
    fun getAllNotes(): LiveData<List<Form>> = formRepository.getAllForm()
}