package com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.use_case

import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.repository.NotesRepository

class GetNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int) : Note? {
        return repository.getNoteById(id)
    }
}