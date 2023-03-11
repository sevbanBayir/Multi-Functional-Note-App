package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sevbanbayir.multifunctionalnoteapp.ADD_EDIT_NOTE_SCREEN
import com.sevbanbayir.multifunctionalnoteapp.NoteAppState
import com.sevbanbayir.multifunctionalnoteapp.R
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes.components.NoteItem
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes.components.OrderSection
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.ui.theme.LufgaFont

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    appState: NoteAppState,
    viewModel: NotesViewModel = hiltViewModel(),
    openScreen:() -> Unit,
    onClickNoteItem: (noteId: Int,noteColor:Int) -> Unit
) {
    val noteState = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = openScreen) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add_fab_cd))
            }
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            contentColor = MaterialTheme.colors.onBackground,
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 16.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = ParagraphStyle(lineHeight = 72.sp)) {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = LufgaFont,
                                        fontSize = 72.sp
                                    )
                                ) {
                                    append("My\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = LufgaFont,
                                        fontSize = 72.sp
                                    )
                                ) {
                                    append("Notes")
                                }
                            }
                        }
                    )
                }

                OrderSection(
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    },
                    noteOrder = noteState.noteOrder
                )

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(count = 2),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(noteState.notes) { note ->
                        NoteItem(
                            item = note,
                            onClickDeleteIcon = {
                                viewModel.onEvent(NotesEvent.DeleteNote(note))
                            },
                            onClick = {
                                appState.navigate("$ADD_EDIT_NOTE_SCREEN?noteId=${note.id}&noteColor=${note.color}")
                            }
                        )
                    }
                }
            }
        }
    }
}