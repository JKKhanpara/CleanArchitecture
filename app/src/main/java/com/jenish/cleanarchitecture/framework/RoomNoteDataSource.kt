package com.jenish.cleanarchitecture.framework

import android.content.Context
import com.jenish.cleanarchitecture.framework.db.DatabaseService
import com.jenish.cleanarchitecture.framework.db.NoteEntity
import com.jenish.core.data.Note
import com.jenish.core.repository.NoteDataSource

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
class RoomNoteDataSource(context: Context) : NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id).toNote()

    override suspend fun getAll() = noteDao.getAllNotes().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.removeEntity(NoteEntity.fromNote(note))
}