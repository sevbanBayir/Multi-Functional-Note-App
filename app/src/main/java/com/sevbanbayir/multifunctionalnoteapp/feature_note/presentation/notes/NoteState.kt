package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes

import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.NoteOrder
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val isOrderSectionVisible: Boolean= false,
    val noteOrder: NoteOrder = NoteOrder.Title(OrderType.Ascending)
)
