package com.example.pankynoteapp.repository

import com.example.pankynoteapp.data.NoteDatabaseDao
import com.example.pankynoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
    fun getNotes():Flow<List<Note>> = noteDatabaseDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
}