package com.jenish.cleanarchitecture.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.jenish.cleanarchitecture.R
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : Fragment() {

    private val viewModel by viewModels<NoteListViewModel>()

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
                goToBackScreen()
            }
        }

        fabNoteDelete.apply {
            setOnClickListener {
                goToBackScreen()
            }
        }

    }

    private fun goToBackScreen() {
        Navigation.findNavController(main_detail_container).popBackStack()
    }
}
