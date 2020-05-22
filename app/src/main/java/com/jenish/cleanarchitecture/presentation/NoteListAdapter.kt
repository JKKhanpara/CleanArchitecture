package com.jenish.cleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jenish.cleanarchitecture.R
import com.jenish.core.data.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Developed By JENISH KHANPARA on 18 May 2020.
 */
class NoteListAdapter(private val clickListener: ((Note) -> Unit)?) :
    ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val noteCard = view.note_card
        private val noteTitle = view.textViewTitle
        private val noteContent = view.textViewContent
        private val noteDate = view.textViewDate

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteContent.text = note.content
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss", Locale.getDefault())
            val formattedDate = sdf.format(Date(note.updateTime))
            noteDate.text = formattedDate
            noteCard.setOnClickListener {
                clickListener?.invoke(note)
            }
        }
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }


}