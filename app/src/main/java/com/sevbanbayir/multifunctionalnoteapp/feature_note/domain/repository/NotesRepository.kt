package com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.repository

import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getAllNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}