package com.example.noteapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "note_id")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_description")
    val description: String,

    @ColumnInfo(name = "note_entry_date")
    val entryDate: Date = Date()
) {
    fun getFormattedEntryDate(pattern: String = "EEE MMM d HH:mm aaa"): String {
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return format.format(entryDate)
    }
}
