package com.example.pankynoteapp.data


import com.example.pankynoteapp.model.Note


class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(name = "A good day", description = "We went on a vacation by the lake"),
            Note(name = "Android Compose", description = "Working on Android Compose course today"),
            Note(name = "Keep at it...", description = "Sometimes things just happen"),
            Note(name = "A movie day", description = "Watching a movie with family today"),
            Note(name = "A movie day", description = "Watching a movie with family today"),
            Note(name = "A movie day", description = "Watching a movie with family today"),
            Note(name = "A movie day", description = "Watching a movie with family today"),
            Note(name = "A movie day", description = "Watching a movie with family today"),
            Note(name = "A movie day", description = "Watching a movie with family today"),
            Note(name = "A movie day", description = "Watching a movie with family")
        )
    }
}