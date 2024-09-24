package com.example.noteapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.ui.components.NoteButton
import com.example.noteapp.ui.components.NoteInputText

@Composable
fun NoteScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AppBar()
        Body()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(
                onClick = { },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Notification Icon"
                    )
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color(0xFFDADFE3))
    )
}

@Composable
private fun Body() {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        NoteInputText(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            label = "Title",
            onTextChange = { text ->
                if (text.all { ch -> ch.isLetter() || ch.isWhitespace() }) {
                    title = text
                }
            }
        )
        NoteInputText(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            text = description,
            label = "Add a note",
            onTextChange = {
            }
        )
        NoteButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "Save",
            onClick = { }
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
    }
}