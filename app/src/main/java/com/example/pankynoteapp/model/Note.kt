package com.example.pankynoteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id:UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_name")
    val name:String,

    @ColumnInfo(name = "description")
    val description:String,

    @ColumnInfo(name = "date")
    val entryDate: Date = Date.from(Instant.now())
)