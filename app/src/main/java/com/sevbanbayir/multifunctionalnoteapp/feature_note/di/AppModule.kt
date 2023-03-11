package com.sevbanbayir.multifunctionalnoteapp.feature_note.di

import android.app.Application
import androidx.room.Room
import com.sevbanbayir.multifunctionalnoteapp.feature_note.data.data_source.NoteDao
import com.sevbanbayir.multifunctionalnoteapp.feature_note.data.data_source.NoteDatabase
import com.sevbanbayir.multifunctionalnoteapp.feature_note.data.repository.NotesRepositoryImpl
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.repository.NotesRepository
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application) = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        NoteDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NotesRepository) = NoteUseCases(
        addNote = AddNote(repository),
        deleteNote = DeleteNote(repository),
        getAllNotes = GetAllNotes(repository),
        updateNote = UpdateNote(repository),
        getNote = GetNote(repository)
    )

    @Provides
    @Singleton //Get dao from db so that there is no need to provide dao with seperate function.
    fun provideNotesRepository(db: NoteDatabase) : NotesRepository = NotesRepositoryImpl(dao = db.getNoteDao())

}