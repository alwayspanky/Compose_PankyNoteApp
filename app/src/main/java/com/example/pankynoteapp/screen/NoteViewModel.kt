package com.example.pankynoteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pankynoteapp.data.NotesDataSource
import com.example.pankynoteapp.model.Note
import com.example.pankynoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository:NoteRepository): ViewModel() {

//    private var noteList = mutableStateListOf<Note>()
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()


    init {
//        noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
           noteRepository.getNotes().distinctUntilChanged().collect{ listOfNotes ->
                if (listOfNotes.isEmpty()){
                    Log.d("NoteListTag", "Empty note list ")
                }else{
                    _noteList.value = listOfNotes
                }
           }

        }
    }

    fun addNote(note: Note) = viewModelScope.launch { noteRepository.addNote(note) }

    fun removeNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

//    fun getAllNote():List<Note>{
//       return noteList
//    }
}