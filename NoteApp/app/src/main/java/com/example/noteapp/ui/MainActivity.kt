package com.example.noteapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.data.datasource.NoteDataSource
import com.example.noteapp.ui.screen.NoteScreen
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            App { innerPadding ->
                val noteVM: NoteViewModel by viewModels()
                val noteList = noteVM.noteList.collectAsState().value
                NoteScreen(
                    modifier = Modifier.padding(innerPadding),
                    notes = noteList,
                    onAddNote = { noteVM.addNote(it) },
                    onRemoveNote = { noteVM.removeNote(it) }
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
            notes = NoteDataSource().loadNotes()
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