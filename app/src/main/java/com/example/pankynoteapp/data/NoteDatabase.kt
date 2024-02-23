package com.example.pankynoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.pankynoteapp.model.Note
import com.example.pankynoteapp.util.DateConverter
import com.example.pankynoteapp.util.UUIDConverter

@Database(entities = [Note::class] , version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDatabase(): RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}