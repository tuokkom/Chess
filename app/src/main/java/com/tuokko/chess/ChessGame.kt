package com.tuokko.chess

import android.app.ActionBar
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity

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
        val layerDrawable: LayerDrawable
        var chessPiece: ChessPiece? = null
        when (row) {
            1 -> {
                chessPiece = ChessPiece(row, column)
                layerDrawable = getPiece(false, column, background)
            }
            2 -> {
                chessPiece = ChessPiece(row, column)
                val icon = resources.getDrawable(R.drawable.ic_black_pawn)
                val layers = arrayOf<Drawable>(background, icon)
                layerDrawable = LayerDrawable(layers)
            }
            7 -> {
                chessPiece = ChessPiece(row, column)
                val icon = resources.getDrawable(R.drawable.ic_white_pawn)
                val layers = arrayOf<Drawable>(background, icon)
                layerDrawable = LayerDrawable(layers)
            }
            8 -> layerDrawable = getPiece(true, column, background)
            else -> {
                chessPiece = ChessPiece(row, column)
                val layers = arrayOf<Drawable>(background)
                layerDrawable = LayerDrawable(layers)
            }
        }
        boardRect.background = layerDrawable
        boardRect.chessPiece = chessPiece

        boardRect.setOnClickListener {view ->
            if (view is BoardRectangle) {
                Log.d(CLASS_NAME, "onClickListener()", "White clicked: ${view.isWhite}")
                Log.d(CLASS_NAME, "onClickListener()", "Column: ${view.columnNumber}")
                Log.d(CLASS_NAME, "onClickListener()", "Row: ${view.rowNumber}")
                handleBoardClick(view)
            }
        }
        return boardRect
    }

    private fun getPiece(isWhite: Boolean, column: Int, background: Drawable): LayerDrawable {
        val icon: Drawable
        when (column) {
            1 -> icon = getTower(isWhite)
            2 -> icon = getHorse(isWhite)
            3 -> icon = getBishop(isWhite)
            4 -> icon = getQueen(isWhite)
            5 -> icon = getKing(isWhite)
            6 -> icon = getBishop(isWhite)
            7 -> icon = getHorse(isWhite)
            8 -> icon = getTower(isWhite)
            else -> {
                Log.d(CLASS_NAME, "getPiece()", "Invalid column number")
                icon = background
            }
        }
        val layers = arrayOf<Drawable>(background, icon)
        return LayerDrawable(layers)
    }

    private fun getHorse(isWhite: Boolean): Drawable {
        return if (isWhite) {
            resources.getDrawable(R.drawable.ic_white_horse)
        } else {
            resources.getDrawable(R.drawable.ic_black_horse)
        }
    }

    private fun getTower(isWhite: Boolean): Drawable {
        return if (isWhite) {
            resources.getDrawable(R.drawable.ic_white_tower)
        } else {
            resources.getDrawable(R.drawable.ic_black_tower)
        }
    }

    private fun getBishop(isWhite: Boolean): Drawable {
        return if (isWhite) {
            resources.getDrawable(R.drawable.ic_white_bishop)
        } else {
            resources.getDrawable(R.drawable.ic_black_bishop)
        }
    }

    private fun getQueen(isWhite: Boolean): Drawable {
        return if (isWhite) {
            resources.getDrawable(R.drawable.ic_white_queen)
        } else {
            resources.getDrawable(R.drawable.ic_black_queen)
        }
    }

    private fun getKing(isWhite: Boolean): Drawable {
        return if (isWhite) {
            resources.getDrawable(R.drawable.ic_white_king)
        } else {
            resources.getDrawable(R.drawable.ic_black_king)
        }
    }

    private fun handleBoardClick(boardPart: BoardRectangle) {

    }
}