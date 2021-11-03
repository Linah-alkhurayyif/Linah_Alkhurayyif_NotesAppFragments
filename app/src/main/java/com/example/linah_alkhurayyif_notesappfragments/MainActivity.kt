package com.example.linah_alkhurayyif_notesappfragments

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation


class MainActivity : AppCompatActivity() {
    private lateinit var nav_controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_controller = Navigation.findNavController(this, R.id.fragmentContainerView)
    }
    override fun onSupportNavigateUp(): Boolean {
        return nav_controller.navigateUp()
    }
}