package com.example.noteapp.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: Date = Date()
) {
    fun getFormattedEntryDate(pattern: String = "EEE MMM d HH:mm aaa"): String {
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return format.format(entryDate)
    }
}
