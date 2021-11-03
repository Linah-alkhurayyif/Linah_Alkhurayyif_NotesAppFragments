package com.example.linah_alkhurayyif_notesappfragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val notes: MutableLiveData<List<Note>> = MutableLiveData()
    private var db: FirebaseFirestore = Firebase.firestore

    fun getNotes(): LiveData<List<Note>>{
        return notes
    }

    fun addNote(noteText: String){
        val note = hashMapOf(
            "note" to noteText,
        )

        db.collection("notes")
            .add(note)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                getNoteData()
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }
    fun getNoteData(){
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                val tempNotes = arrayListOf<Note>()
                for (document in result) {
                    document.data.map { (key, value) -> tempNotes.add(Note(document.id, value.toString())) }
                }
                notes.postValue(tempNotes)
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
    }

    fun editNote(noteID: String, note: String){
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(document.id == noteID){
                        db.collection("notes").document(noteID).update("note", note)
                    }
                }
                getNoteData()
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
    }

    fun deleteNote(noteID: String){
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(document.id == noteID){
                        db.collection("notes").document(noteID).delete()
                    }
                }
                getNoteData()
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error ...", exception)
            }
    }
    }