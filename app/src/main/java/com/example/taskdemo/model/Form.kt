package com.example.taskdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Form")
data class  Form(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,

    @ColumnInfo(name = "survey") var survey:String? = null,
    @ColumnInfo(name = "village") var village: String? = null,
    @ColumnInfo(name = "district") var district: String? = null,
    @ColumnInfo(name = "field4") var field4: String? = null,
    @ColumnInfo(name = "field5") var field5: String? = null,
    @ColumnInfo(name = "field6") var field6: String? = null,
    @ColumnInfo(name = "field7") var field7: String? = null,
    @ColumnInfo(name = "field8") var field8: String? = null,
    @ColumnInfo(name = "field9") var field9: String? = null,
    @ColumnInfo(name = "field10") var field10: String? = null,
)
//@ColumnInfo(name = "image") var image:String? = null,