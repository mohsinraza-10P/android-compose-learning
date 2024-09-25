package com.example.noteapp.data.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Date
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: Date = Date()
)
