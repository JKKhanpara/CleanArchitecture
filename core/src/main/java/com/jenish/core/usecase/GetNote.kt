package com.jenish.core.usecase

import com.jenish.core.repository.NoteRepository

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}