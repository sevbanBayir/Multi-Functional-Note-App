package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.add_edit_note.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import kotlin.math.sin

@Composable
fun TransparentTextField(
    text: String,
    @StringRes
    hint: Int,
    isHintVisible: Boolean,
    singleLine: Boolean,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onFocusChange: (FocusState) -> Unit,
    onValueChange: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        BasicTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) },
            singleLine = singleLine,
            textStyle = textStyle
        )
        if (isHintVisible)
            Text(text = stringResource(id = hint), style = textStyle, color = Color.Gray)
    }
}