package com.example.noteapp.data.database

import androidx.room.TypeConverter
import java.util.Date
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun fromUUID(id: UUID) = id.toString()

    @TypeConverter
    fun toUUID(id: String) = UUID.fromString(id)
}