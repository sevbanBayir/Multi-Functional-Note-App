package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.add_edit_note

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevbanbayir.multifunctionalnoteapp.R
import com.sevbanbayir.multifunctionalnoteapp.common.presentation.snackbar.SnackbarManager
import com.sevbanbayir.multifunctionalnoteapp.common.presentation.snackbar.SnackbarMessage
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = R.string.title_hint
        )
    )
        private set

    var noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = R.string.content_hint
        )
    )
        private set

    var noteColor = mutableStateOf(Note.noteColors.random().toArgb())
        private set

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            println(noteId)
            if (noteId != -1) {
                currentNoteId = noteId
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                noteTitle.value = noteTitle.value.copy(
                    text = event.title
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                noteColor.value = event.color
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                noteContent.value = noteContent.value.copy(
                    text = event.content
                )
            }
            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                id = currentNoteId,
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                color = noteColor.value,
                            )
                        )
                    } catch (e: java.lang.Exception) {
                        SnackbarManager.showMessage(
                            SnackbarMessage.StringSnackbar(e.message.toString())
                        )
                    }
                }
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
        }
    }
}
