package com.example.linah_alkhurayyif_notesappfragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager


class FragmentUpdate : Fragment() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // We use 'requireActivity()' to access MainActivity here
        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val updateNote_et = view.findViewById<EditText>(R.id.updateNote_et)
        view.findViewById<Button>(R.id.updateNote_btn).setOnClickListener {
            val noteId = sharedPreferences.getString("NoteId", "").toString()
            mainViewModel.editNote(noteId, updateNote_et.text.toString())
            with(sharedPreferences.edit()) {
                putString("NoteId", updateNote_et.text.toString())
                apply()
            }

            findNavController().navigate(R.id.action_fragmentUpdate_to_fragmentHome)

        }

        return view
    }

}