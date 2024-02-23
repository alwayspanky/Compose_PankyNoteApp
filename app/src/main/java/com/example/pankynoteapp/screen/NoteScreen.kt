@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pankynoteapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pankynoteapp.R
import com.example.pankynoteapp.component.NoteButton
import com.example.pankynoteapp.component.NoteTextField
import com.example.pankynoteapp.data.NotesDataSource
import com.example.pankynoteapp.model.Note


@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote:(Note)->Unit,
    onRemoveNote:(Note)->Unit
){

    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(6.dp)
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        TopAppBar(
            title = {
           Text(text = stringResource(id = R.string.app_name))
                 },
            colors = TopAppBarDefaults
                .topAppBarColors(
                    containerColor = Color.Red),
            actions = {
                Icon(imageVector = Icons.Default.Notifications,
                    contentDescription = "icon")
            }
        )

        //content
        NoteTextField(
            text = name,
            label = "Name",
            onTextChange = {
                if (it.all { char->
                    char.isLetter() || char.isWhitespace()
                    }) name = it
            },
            modifier = Modifier.padding(
                top = 8.dp, bottom = 8.dp
            ))
        NoteTextField(
            text = description,
            label = "Description",
            onTextChange = {
                if (it.all { char->
                    char.isLetter() || char.isWhitespace()
                    }) description = it
            },
            modifier = Modifier.padding(
                top = 8.dp, bottom = 8.dp
            ))
        NoteButton(
            text = "Save",
            onClick = {
                if (name.isNotEmpty() && description.isNotEmpty()){
                    onAddNote(Note(
                        name = name,
                        description = description
                    )
                    )
                    name = ""
                    description = ""
                }

            }
        )
        Divider(modifier = Modifier.padding(top = 8.dp))
        LazyColumn(){
            items(notes){note->
                NoteRow(note = note, onNoteClick = {
                    onRemoveNote(note)
                })
            }
        }
    }
}

@Composable
fun NoteRow(
    note:Note,
    modifier: Modifier =Modifier,
    onNoteClick:(Note)->Unit
){
    Surface(
        modifier
            .padding(8.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 33.dp,
                    bottomStart = 33.dp
                )
            )
            .fillMaxWidth()
            .clickable {
                onNoteClick(note)
            },
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {
        Column(modifier=Modifier.padding(horizontal = 14.dp,
            vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.name)
            Text(text = note.description)
            Text(text = note.entryDate.toString())
        }
    }
}

@Preview
@Composable
fun NotePreview(){
NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {} , onRemoveNote = {})
}