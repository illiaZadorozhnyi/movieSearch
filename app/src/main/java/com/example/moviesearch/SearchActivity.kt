package com.example.moviesearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

const val MESSAGE = "com.example.moviesearch.MESSAGE"

class SearchActivity : AppCompatActivity() {

    private lateinit var inputField: EditText
    private lateinit var searchButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initViews()

        setOnClickListener()
    }

    private fun setOnClickListener() {
        searchButton.setOnClickListener {
            val text = inputField.text.toString()

            val intent = Intent(this, MovieListActivity::class.java).apply {
                putExtra(MESSAGE, text)
            }
            startActivity(intent)
        }
    }

    private fun initViews() {
        inputField = findViewById(R.id.input_field)
        searchButton = findViewById(R.id.button_search)
        searchButton.text = "SEARCH"

    }
}