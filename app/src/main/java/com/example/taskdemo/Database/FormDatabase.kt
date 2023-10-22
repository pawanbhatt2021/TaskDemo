package com.example.taskdemo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskdemo.Dao.FormDao
import com.example.taskdemo.model.Form

@Database(entities = [Form::class],version=1, exportSchema = false)
 abstract class FormDatabase : RoomDatabase() {
      abstract fun getNotesDao():FormDao
      companion object{
          private const val DB_NAME = "Notes_Database.db"
           @Volatile private var INSTANCE: FormDatabase?=null
          private val LOCK=Any()
          operator fun invoke(context: Context)= INSTANCE ?: synchronized(LOCK){
              INSTANCE ?: getDatabaseInstance(context).also {
                  INSTANCE =it
              }
          }
           fun getDatabaseInstance(context:Context): FormDatabase =
              Room.databaseBuilder(context.applicationContext,
                      FormDatabase::class.java,
                      "Notes_Database").build()
      }

}