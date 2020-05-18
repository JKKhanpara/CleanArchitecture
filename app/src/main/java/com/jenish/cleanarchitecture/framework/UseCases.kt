package com.jenish.cleanarchitecture.framework

import com.jenish.core.usecase.AddNote
import com.jenish.core.usecase.GetAllNotes
import com.jenish.core.usecase.GetNote
import com.jenish.core.usecase.RemoveNote

/**
 * Developed By JENISH KHANPARA on 15 May 2020.
 */
data class UseCases(
    val addNote: AddNote,
    val removeNote: RemoveNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote
)