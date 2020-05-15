package com.jenish.core.repository

import com.jenish.core.data.Note

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
interface NoteDataSource {

    suspend fun add(note: Note)

    suspend fun get(id: Long):Note?

    suspend fun getAll():List<Note>

    suspend fun remove(note:Note)
}