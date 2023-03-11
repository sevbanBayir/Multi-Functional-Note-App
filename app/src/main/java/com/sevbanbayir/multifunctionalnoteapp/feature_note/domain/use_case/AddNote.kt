package com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.use_case

import com.sevbanbayir.multifunctionalnoteapp.R.string as AppText
import com.sevbanbayir.multifunctionalnoteapp.common.presentation.snackbar.SnackbarManager
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.repository.NotesRepository

class AddNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()){
            SnackbarManager.showMessage(AppText.empty_note_title)
            return
        }
        if (note.content.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_note_content)
            return
        }
        return repository.insertNote(note)    }
}