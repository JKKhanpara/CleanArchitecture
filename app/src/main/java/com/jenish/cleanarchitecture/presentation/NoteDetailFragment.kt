package com.jenish.cleanarchitecture.presentation

import android.app.Service
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jenish.cleanarchitecture.R
import com.jenish.cleanarchitecture.framework.NoteListViewModel
import com.jenish.core.data.Note
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : Fragment() {

    private val viewModel by viewModels<NoteListViewModel>()

    private lateinit var currentNote: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabNoteSave.apply {
            setOnClickListener {
                if (editTextTitle.text.toString().isNotBlank()
                    || editTextContent.text.toString().isNotBlank()
                ) {
                    hideKeyboard()
                    val time = System.currentTimeMillis()
                    currentNote = Note(
                        editTextTitle.text.toString(),
                        editTextContent.text.toString(),
                        0,
                        time
                    )
                    viewModel.addNote(currentNote)
                } else {
                    goToBackScreen()
                }
            }
        }

        fabNoteDelete.apply {
            setOnClickListener {
                goToBackScreen()
            }
        }

        observeViewModel()

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

    }

    private fun goToBackScreen() {
        Navigation.findNavController(main_detail_container).popBackStack()
    }
}
