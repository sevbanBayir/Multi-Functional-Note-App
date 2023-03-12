package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val title: String): AddEditNoteEvent()
    data class EnteredContent(val content: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val color: Int): AddEditNoteEvent()
    object UpdateNote : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}
