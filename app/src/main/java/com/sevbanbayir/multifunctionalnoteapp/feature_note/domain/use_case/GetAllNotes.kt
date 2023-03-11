package com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.use_case

import androidx.compose.foundation.isSystemInDarkTheme
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.repository.NotesRepository
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.NoteOrder
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotes(
    private val repository: NotesRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Ascending)
    ) : Flow<List<Note>> {
       return repository.getAllNotes().map { notes ->
           when(noteOrder.orderType) {
               is OrderType.Ascending -> {
                   when (noteOrder) {
                       is NoteOrder.Date -> notes.sortedBy { it.date}
                       is NoteOrder.Title -> notes.sortedBy { it.title }
                       is NoteOrder.Color -> notes.sortedBy { it.color }
                   }
               }
               is OrderType.Descending -> {
                   when(noteOrder) {
                       is NoteOrder.Title -> notes.sortedByDescending { it.title }
                       is NoteOrder.Color -> notes.sortedByDescending { it.color }
                       is NoteOrder.Date -> notes.sortedByDescending { it.date }
                   }
               }
           }

       }
    }
}