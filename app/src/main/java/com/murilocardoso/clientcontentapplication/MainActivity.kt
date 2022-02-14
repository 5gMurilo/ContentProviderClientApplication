package com.murilocardoso.clientcontentapplication

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var notesRecyclerView: RecyclerView
    lateinit var refreshNotes: FloatingActionButton

    companion object{
        const val URL = "content://com.example.contentproviderapplication.provider/notes"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRecyclerView = findViewById(R.id.client_recycler)
        refreshNotes = findViewById(R.id.client_refresh_button)
        getContentProvider()
        listener()
    }

    private fun listener() {
        refreshNotes.setOnClickListener {
            getContentProvider()
        }
    }

    private fun getContentProvider() {
        try {
            val data : Uri = Uri.parse(URL)

            val cursor: Cursor? = this.contentResolver.query(
                data,null,null,null,"title")


            notesRecyclerView.adapter = ClientAdapter(cursor as Cursor)
            notesRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            Log.i("CURSOR SIZE", notesRecyclerView.adapter?.itemCount.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}