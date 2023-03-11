package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes


import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.NoteOrder
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.OrderType

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object ToggleOrderSection: NotesEvent()
    object RestoreNote: NotesEvent()
}