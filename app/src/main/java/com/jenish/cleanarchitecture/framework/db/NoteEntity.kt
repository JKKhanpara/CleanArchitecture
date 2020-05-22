package com.jenish.cleanarchitecture.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jenish.core.data.Note

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
@Entity(tableName = "note")
data class NoteEntity(
    var title: String,
    var content: String,

    @ColumnInfo(name = "creation_time")
    var creationTime: Long,

    @ColumnInfo(name = "update_time")
    var updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    companion object {
        fun fromNote(note: Note) =
            NoteEntity(note.title, note.content, note.creationTime, note.updateTime, note.id)
    }

    fun toNote() = Note(title, content, creationTime, updateTime, id)
}