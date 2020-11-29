package com.tuokko.chess

import android.app.ActionBar
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log.d
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tuokko.chess.Log

class ChessGame: AppCompatActivity() {
    private val CLASS_NAME = "ChessGame"

    private lateinit var boardLayout: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.chess_game_activity)
        boardLayout = findViewById(R.id.board_grid)
        setupBoard()
    }

    private fun setupBoard() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val boardRectWidth = ( displayMetrics.widthPixels - 40) / 8

        for (i in 1..8) {
            for (j in 1..8) {
                var rect: TextView
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        rect = createBoardRectangle(true, boardRectWidth)
                    } else {
                        rect = createBoardRectangle(false, boardRectWidth)
                    }
                } else {
                    if (j % 2 != 0) {
                        rect = createBoardRectangle(true, boardRectWidth)
                    } else {
                        rect = createBoardRectangle(false, boardRectWidth)
                    }
                }
                Log.d(CLASS_NAME, "setupBoard()", "New board rectangle width: ${displayMetrics.widthPixels}")

                boardLayout.addView(rect)
            }
        }
    }

    private fun createBoardRectangle(white: Boolean, width: Int): TextView {
        Log.d(CLASS_NAME, "createBoardRectangle()", "New board rectangle")

        val boardRect = TextView(this)

        val layoutParams = ActionBar.LayoutParams(width, width)
        if (white) {
            boardRect.background = resources.getDrawable(R.drawable.board_background_white)
        } else {
            boardRect.background = resources.getDrawable(R.drawable.board_background_brown)
        }
        boardRect.layoutParams = layoutParams
        return boardRect
    }
}