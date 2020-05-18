package com.jenish.cleanarchitecture.framework.db

import androidx.room.*

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteEntity(id: Long): NoteEntity

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<NoteEntity>

    @Delete
    suspend fun removeEntity(noteEntity: NoteEntity)
}