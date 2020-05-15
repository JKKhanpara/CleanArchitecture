package com.jenish.core.repository

import com.jenish.core.data.Note

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
class NoteRepository(private val dataSource: NoteDataSource) {

    suspend fun addNote(note: Note) = dataSource.add(note)

    suspend fun getAllNotes() = dataSource.getAll()

    suspend fun getNote(noteId: Long) = dataSource.get(noteId)

    suspend fun removeNote(note: Note) = dataSource.remove(note)
}