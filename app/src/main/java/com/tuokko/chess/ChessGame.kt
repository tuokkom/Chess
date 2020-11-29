package com.tuokko.chess

import android.app.ActionBar
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
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
                var rect: BoardRectangle
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        rect = createBoardRectangle(true, boardRectWidth, j, i)
                    } else {
                        rect = createBoardRectangle(false, boardRectWidth, j, i)
                    }
                } else {
                    if (j % 2 != 0) {
                        rect = createBoardRectangle(true, boardRectWidth, j, i)
                    } else {
                        rect = createBoardRectangle(false, boardRectWidth, j, i)
                    }
                }
                Log.d(CLASS_NAME, "setupBoard()", "New board rectangle width: ${displayMetrics.widthPixels}")

                boardLayout.addView(rect)
            }
        }
    }

    private fun createBoardRectangle(white: Boolean, width: Int, column: Int, row: Int): BoardRectangle {
        Log.d(CLASS_NAME, "createBoardRectangle()", "New board rectangle")

        val boardRect = BoardRectangle(this)
        val layoutParams = ActionBar.LayoutParams(width, width)
        boardRect.layoutParams = layoutParams
        boardRect.columnNumber = column
        boardRect.rowNumber = row

        val background: Drawable
        if (white) {
            boardRect.isWhite = true
            background = resources.getDrawable(R.drawable.board_background_white)
        } else {
            boardRect.isWhite = false
            background = resources.getDrawable(R.drawable.board_background_brown)
        }

        // Add black pawns
        when (row) {
            2 -> {
                val icon = resources.getDrawable(R.drawable.ic_black_pawn)
                val layers = arrayOf<Drawable>(background, icon)
                val layerDrawable = LayerDrawable(layers)
                boardRect.background = layerDrawable
            }
            7 -> {
                val icon = resources.getDrawable(R.drawable.ic_white_pawn)
                val layers = arrayOf<Drawable>(background, icon)
                val layerDrawable = LayerDrawable(layers)
                boardRect.background = layerDrawable
            }
            else -> {
                boardRect.background = background
            }
        }

        boardRect.setOnClickListener {view ->
            if (view is BoardRectangle) {
                Log.d(CLASS_NAME, "onClickListener()", "White clicked: ${view.isWhite}")
                Log.d(CLASS_NAME, "onClickListener()", "Column: ${view.columnNumber}")
                Log.d(CLASS_NAME, "onClickListener()", "Row: ${view.rowNumber}")

                /*
                val background = view.background
                val icon  = resources.getDrawable(R.drawable.ic_white_king)
                val layers = arrayOf<Drawable>(background, icon)
                val layerDrawable = LayerDrawable(layers)
                view.background = layerDrawable

                 */
            }
        }
        return boardRect
    }
}