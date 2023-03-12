package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.model.Note
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.ui.theme.LufgaFont
import kotlin.random.Random

@Composable
fun NoteItem(item: Note, onClickDeleteIcon: () -> Unit, onClick:() -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable( onClick = onClick)
        .height(item.height.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(Color(item.color))
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = item.title,
                fontFamily = LufgaFont,
                fontSize = MaterialTheme.typography.h3.fontSize,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.content,
                fontFamily = LufgaFont,
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = Color.Black
            )
        }
        IconButton(
            onClick = onClickDeleteIcon,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note icon."
            )
        }
    }
}