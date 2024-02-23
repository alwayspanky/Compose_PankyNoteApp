package com.example.pankynoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pankynoteapp.data.NotesDataSource
import com.example.pankynoteapp.model.Note
import com.example.pankynoteapp.screen.NoteScreen
import com.example.pankynoteapp.screen.NoteViewModel
import com.example.pankynoteapp.ui.theme.PankyNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PankyNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    val noteViewModel = viewModel<NoteViewModel>()
                    NoteApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NoteApp(
    noteViewModel: NoteViewModel
){
    val noteList = noteViewModel.noteList.collectAsState().value

    NoteScreen(notes = noteList,
        onAddNote = {
           noteViewModel.addNote(it)
        },
        onRemoveNote = {
           noteViewModel.removeNote(it)
        })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PankyNoteAppTheme {
        NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
    }
}