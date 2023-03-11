package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.add_edit_note

import androidx.annotation.StringRes

data class NoteTextFieldState(
    val text: String= "",
    @StringRes
    val hint: Int,
    val isHintVisible: Boolean = true
)
