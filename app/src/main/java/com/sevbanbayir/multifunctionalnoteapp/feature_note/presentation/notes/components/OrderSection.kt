package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.notes.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sevbanbayir.multifunctionalnoteapp.R
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.NoteOrder
import com.sevbanbayir.multifunctionalnoteapp.feature_note.domain.util.OrderType
import com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.ui.theme.LufgaFont

@Composable
fun OrderSection(
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        OrderOutlinedButton(
            onClick = {
                onOrderChange(NoteOrder.Color(noteOrder.orderType))
            },
            stringId = R.string.sort_color,
            selected = noteOrder is NoteOrder.Color
        )

        OrderOutlinedButton(
            onClick = {
                onOrderChange(NoteOrder.Title(noteOrder.orderType))
            },
            stringId = R.string.sort_title,
            selected = noteOrder is NoteOrder.Title
        )

        OrderOutlinedButton(
            onClick = {
                onOrderChange(NoteOrder.Date(noteOrder.orderType))
            },
            stringId = R.string.sort_date,
            selected = noteOrder is NoteOrder.Date
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        OrderOutlinedButton(
            onClick = {
                onOrderChange(noteOrder.copy(orderType = OrderType.Ascending))
            },
            stringId = R.string.sort_ascending,
            selected = noteOrder.orderType is OrderType.Ascending
        )
        OrderOutlinedButton(
            onClick = {
                onOrderChange(noteOrder.copy(orderType = OrderType.Descending))
            },
            stringId = R.string.sort_descending,
            selected = noteOrder.orderType is OrderType.Descending
        )
    }
}

@Composable
fun OrderOutlinedButton(
    selected: Boolean,
    onClick: () -> Unit,
    @StringRes stringId: Int
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .alpha(if (selected) 1f else 0.2f),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(2.dp, Color.White),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
    ) {
        Text(
            text = stringResource(id = stringId),
            fontFamily = LufgaFont,
            modifier = Modifier.padding(6.dp),
            fontSize = 24.sp,
            color = Color.White
        )
    }
}