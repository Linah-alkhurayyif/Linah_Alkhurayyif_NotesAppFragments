package com.example.linah_alkhurayyif_notesappfragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val fragmentHome: FragmentHome): RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {
    private var notes = emptyList<Note>()

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = notes[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.note_tv).text = note.noteText
            findViewById<ImageButton>(R.id.update).setOnClickListener {
                with(fragmentHome.sharedPreferences.edit()) {
                    putString("NoteId", note.id)
                    apply()
                }
                fragmentHome.findNavController().navigate(R.id.action_fragmentHome_to_fragmentUpdate)
            }
            findViewById<ImageButton>(R.id.del).setOnClickListener {
                fragmentHome.mainViewModel.deleteNote(note.id)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun update(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}
