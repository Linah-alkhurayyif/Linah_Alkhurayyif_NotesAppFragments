package com.example.linah_alkhurayyif_notesappfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FragmentHome : Fragment() {
    lateinit var mainViewModel: MainViewModel
    private lateinit var rvAdapter: NoteAdapter
    private lateinit var note_recyclerView: RecyclerView
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val note_et=  view.findViewById<EditText>(R.id.note_et)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getNotes().observe(viewLifecycleOwner, {
                notes -> rvAdapter.update(notes)
        })
        note_recyclerView = view.findViewById(R.id.note_recyclerView)

        updateRV()
        view.findViewById<FloatingActionButton>(R.id.note_btn).setOnClickListener {
            if(note_et.text.toString() !=""){
                mainViewModel.addNote(note_et.text.toString())
                note_et.text.clear()
                note_et.clearFocus()
            }

        }

        return view
    }
    override fun onResume() {
        super.onResume()

        mainViewModel.getNoteData()
    }
fun updateRV(){
    mainViewModel.getNoteData()
    rvAdapter = NoteAdapter(this)
    note_recyclerView.adapter = rvAdapter
    note_recyclerView.layoutManager = LinearLayoutManager(requireContext())

}
}