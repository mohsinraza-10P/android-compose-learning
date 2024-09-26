package com.example.noteapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.data.models.Note
import com.example.noteapp.ui.components.NoteButton
import com.example.noteapp.ui.components.NoteInputText
import com.example.noteapp.ui.theme.AppBarBg
import com.example.noteapp.ui.theme.NoteItemBg

@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    notes: List<Note> = emptyList(),
    onAddNote: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {}
) {
    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        AppBar()
        Body(title, description, notes, onAddNote, onRemoveNote)
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
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = AppBarBg)
    )
}

@Composable
private fun Body(
    title: MutableState<String>,
    description: MutableState<String>,
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        NoteInputText(
            modifier = Modifier.fillMaxWidth(),
            text = title.value,
            label = "Title",
            onTextChange = { text ->
                if (text.all { ch -> ch.isLetter() || ch.isWhitespace() }) {
                    title.value = text
                }
            }
        )
        NoteInputText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = description.value,
            label = "Add a note",
            onTextChange = { text ->
                if (text.all { ch -> ch.isLetter() || ch.isWhitespace() }) {
                    description.value = text
                }
            }
        )
        NoteButton(
            modifier = Modifier.padding(top = 24.dp),
            text = "Save",
            onClick = {
                if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                    onAddNote(
                        Note(
                            title = title.value,
                            description = description.value
                        )
                    )
                    title.value = ""
                    description.value = ""
                    Toast.makeText(context, "Note Added.", Toast.LENGTH_SHORT).show()
                    keyboardController?.hide()
                }
            }
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onRemoveNote = { onRemoveNote(it) })
            }
        }
    }
}

@Composable
private fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onRemoveNote: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)),
        color = NoteItemBg,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                Text(text = note.description, style = MaterialTheme.typography.titleSmall)
                Text(
                    text = note.getFormattedEntryDate(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = { onRemoveNote(note) },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Remove Note",
                    )
                },
            )
        }
    }
}
