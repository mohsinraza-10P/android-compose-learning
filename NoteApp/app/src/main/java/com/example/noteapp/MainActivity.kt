package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.data.datasource.NotesDataSource
import com.example.noteapp.data.models.Note
import com.example.noteapp.ui.screens.NoteScreen
import com.example.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            App { innerPadding ->
                val notes = remember {
                    mutableListOf<Note>()
                }
                NoteScreen(
                    modifier = Modifier.padding(innerPadding),
                    notes = notes,
                    onAddNote = { notes.add(it) },
                    onRemoveNote = { notes.remove(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    App { innerPadding ->
        NoteScreen(
            modifier = Modifier.padding(innerPadding),
            notes = NotesDataSource().loadNotes()
        )
    }
}

@Composable
private fun App(content: @Composable (PaddingValues) -> Unit) {
    NoteAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content(innerPadding)
        }
    }
}