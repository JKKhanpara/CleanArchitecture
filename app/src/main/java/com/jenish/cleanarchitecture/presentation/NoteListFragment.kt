package com.jenish.cleanarchitecture.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jenish.cleanarchitecture.R
import com.jenish.cleanarchitecture.framework.NoteListViewModel
import kotlinx.android.synthetic.main.note_list_fragment.*

class NoteListFragment : Fragment() {

    private val noteListAdapter = NoteListAdapter {
        goToDetailNote(it.id)
    }

    private val viewModel by viewModels<NoteListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabCreate.apply {
            setOnClickListener {
                goToDetailNote()
            }
        }

        notesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            notesList.visibility = View.VISIBLE
            noteListAdapter.submitList(it)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun goToDetailNote(id: Long = 0L) {
        val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(id)
        Navigation.findNavController(main_list_container).navigate(action)
    }

}
