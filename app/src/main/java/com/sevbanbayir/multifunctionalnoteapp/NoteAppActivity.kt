package com.sevbanbayir.multifunctionalnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.ui.theme.MultiFunctionalNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MultiFunctionalNoteApp() }
    }
}
