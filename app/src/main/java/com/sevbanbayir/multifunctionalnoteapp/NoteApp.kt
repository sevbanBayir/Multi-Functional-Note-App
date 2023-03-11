package com.sevbanbayir.multifunctionalnoteapp

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sevbanbayir.multifunctionalnoteapp.common.presentation.snackbar.SnackbarManager
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes.NotesScreen
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.ui.theme.MultiFunctionalNoteAppTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun MultiFunctionalNoteApp() {
    MultiFunctionalNoteAppTheme {
        val appState = rememberAppState()
        Scaffold(
            scaffoldState = appState.scaffoldState,
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    snackbar = { snackbarData ->
                        Snackbar(snackbarData = snackbarData)
                    }
                )
            }
        ) {innerPaddingModifier ->
            NavHost(
                navController = appState.navController as NavHostController,
                startDestination = NOTES_SCREEN,
                modifier = androidx.compose.ui.Modifier.padding(innerPaddingModifier)
            ) {
                noteGraph(appState)
            }
        }
        
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember (
        navController,
        scaffoldState,
        resources,
        snackbarManager,
        coroutineScope
    ){
        NoteAppState(
            navController = navController,
            scaffoldState = scaffoldState,
            snackbarManager = snackbarManager,
            resources = resources,
            coroutineScope = coroutineScope
        )
    }


@Composable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.noteGraph(appState: NoteAppState) {
    composable(NOTES_SCREEN) {
        NotesScreen(
            appState = appState,
            openScreen = {
                appState.navigate("$ADD_EDIT_NOTE_SCREEN$NOTE_ID_ARG$NOTE_COLOR_ARG") },
            onClickNoteItem = { noteColor, noteId ->
                appState.navigate("$ADD_EDIT_NOTE_SCREEN?noteId={$noteId}&noteColor={$noteColor}") }
        )
    }
    composable(
        route = "$ADD_EDIT_NOTE_SCREEN$NOTE_ID_ARG$NOTE_COLOR_ARG",
        arguments = listOf(
            navArgument(NOTE_ID) {
                type = NavType.IntType
                defaultValue = NOTE_DEFAULT_ID },
            navArgument(NOTE_COLOR){
                type = NavType.IntType
                defaultValue = NOTE_DEFAULT_COLOR }
        )
    ) {
        val noteColor = it.arguments?.getInt(NOTE_COLOR) ?: -1
        AddEditNoteScreen(
            navigateBackToListScreen = { appState.navigate(NOTES_SCREEN) },
            noteColor = noteColor
        )
    }
}