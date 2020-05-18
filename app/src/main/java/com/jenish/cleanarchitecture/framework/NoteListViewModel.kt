package com.jenish.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jenish.core.data.Note
import com.jenish.core.repository.NoteRepository
import com.jenish.core.usecase.AddNote
import com.jenish.core.usecase.GetAllNotes
import com.jenish.core.usecase.GetNote
import com.jenish.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel(application: Application) : AndroidViewModel(application) {
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val repository = NoteRepository(RoomNoteDataSource(application))

    val useCases = UseCases(
        AddNote(repository),
        RemoveNote(repository),
        GetAllNotes(repository),
        GetNote(repository)
    )

    val saved = MutableLiveData<Boolean>()

    fun addNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }


}
