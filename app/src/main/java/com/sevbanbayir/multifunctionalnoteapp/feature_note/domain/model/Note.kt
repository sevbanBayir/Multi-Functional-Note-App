package com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.ui.theme.*

@Entity(tableName = "note")
data class Note(
    @PrimaryKey
    val id: Int?,
    val title: String,
    val content: String,
    val color: Int,
    val date: Long = System.currentTimeMillis(),
) {
    companion object {
        val noteColors = listOf(DarkYellow, Beije, Green, BabyBlue, Orange)
    }
}