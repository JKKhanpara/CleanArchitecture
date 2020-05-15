package com.jenish.core.usecase

import com.jenish.core.data.Note
import com.jenish.core.repository.NoteRepository

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}