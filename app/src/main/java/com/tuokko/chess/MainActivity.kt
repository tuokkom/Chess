package com.tuokko.chess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newGameButton = findViewById<TextView>(R.id.start_new_game_button)
        newGameButton.setOnClickListener {
            val newGameIntent = Intent(this, ChessGame::class.java)
            startActivity(newGameIntent)
        }
    }
}