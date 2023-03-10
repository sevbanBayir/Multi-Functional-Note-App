package com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.use_case

data class NoteUseCases(
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val updateNote: UpdateNote
)