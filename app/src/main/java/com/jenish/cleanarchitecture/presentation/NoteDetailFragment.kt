package com.jenish.cleanarchitecture.presentation

import android.app.AlertDialog
import android.app.Service
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jenish.cleanarchitecture.R
import com.jenish.cleanarchitecture.framework.NoteDetailViewModel
import com.jenish.core.data.Note
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : Fragment() {

    private val viewModel by viewModels<NoteDetailViewModel>()

    private lateinit var currentNote: Note
    private var noteId: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            noteId = NoteDetailFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.getNote(noteId)
            fabNoteDelete.apply {
                show()
                setOnClickListener {
                    showConfirmationDialog()
                }
            }
        }

        fabNoteSave.apply {
            setOnClickListener {
                if (editTextTitle.text.toString().isNotBlank()
                    || editTextContent.text.toString().isNotBlank()
                ) {
                    hideKeyboard()
                    val time = System.currentTimeMillis()
                    if (!::currentNote.isInitialized) {
                        currentNote = Note(
                            editTextTitle.text.toString(),
                            editTextContent.text.toString(),
                            0,
                            time
                        )
                    } else {
                        currentNote.title = editTextTitle.text.toString()
                        currentNote.content = editTextContent.text.toString()
                        currentNote.updateTime = time
                    }
                    viewModel.addNote(currentNote)
                } else {
                    goToBackScreen()
                }
            }
        }



        observeViewModel()

    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(context)
            .setTitle("Delete Note!")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes", { dialog, which ->
                viewModel.deleteNote(currentNote)
            })
            .setNegativeButton("No", { dialog, which ->
                dialog.dismiss()
            })
            .create()
            .show()

    }

    private fun hideKeyboard() {

        val imm =
            requireContext().getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(main_detail_container.windowToken, 0)

    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "Done!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Something went wrong, please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                currentNote = note
                editTextTitle.setText(
                    it.title, TextView.BufferType.EDITABLE
                )
                editTextContent.setText(
                    it.content, TextView.BufferType.EDITABLE
                )
            }
        })
    }

    private fun goToBackScreen() {
        Navigation.findNavController(main_detail_container).popBackStack()
    }
}
