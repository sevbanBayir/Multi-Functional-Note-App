package com.sevbanbayir.multifunctionalnoteapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}