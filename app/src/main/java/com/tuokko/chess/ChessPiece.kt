package com.tuokko.chess

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.GridLayout
import androidx.core.view.get

class ChessPiece(xInitial: Int, yInitial: Int, ctx: Context) {
    private val CLASS_NAME = "ChessPiece"

    enum class PieceValue {
        PAWN, TOWER, HORSE, BISHOP, KING, QUEEN
    }

    private val context = ctx
    var color: ChessGame.PlayerColor? = null
    var value: PieceValue? = null
    var xLocation: Int? = null
    var yLocation: Int? = null
    var icon: Drawable? = null

    init {
        xLocation = xInitial
        yLocation = yInitial
        when (yInitial) {
            1 -> {
                color = ChessGame.PlayerColor.BLACK
                initPieceValueAndIcon(xInitial, false)
            }
            2 -> {
                color = ChessGame.PlayerColor.BLACK
                value = PieceValue.PAWN
                icon = context.getDrawable(R.drawable.ic_black_pawn)
            }
            7 -> {
                color = ChessGame.PlayerColor.WHITE
                value = PieceValue.PAWN
                icon = context.getDrawable(R.drawable.ic_white_pawn)
            }
            8 -> {
                color = ChessGame.PlayerColor.WHITE
                initPieceValueAndIcon(xInitial, true)
            }
        }
    }

    private fun initPieceValueAndIcon(xInitial: Int, isWhite: Boolean) {
        when (xInitial) {
            1 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_tower)
                } else {
                    context.getDrawable(R.drawable.ic_black_tower)
                }
                value = PieceValue.TOWER
            }
            2 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_horse)
                } else {
                    context.getDrawable(R.drawable.ic_black_horse)
                }
                value = PieceValue.HORSE
            }
            3 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_bishop)
                } else {
                    context.getDrawable(R.drawable.ic_black_bishop)
                }
                value = PieceValue.BISHOP
            }
            4 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_queen)
                } else {
                    context.getDrawable(R.drawable.ic_black_queen)
                }
                value = PieceValue.QUEEN
            }
            5 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_king)
                } else {
                    context.getDrawable(R.drawable.ic_black_king)
                }
                value = PieceValue.KING
            }
            6 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_bishop)
                } else {
                    context.getDrawable(R.drawable.ic_black_bishop)
                }
                value = PieceValue.BISHOP
            }
            7 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_horse)
                } else {
                    context.getDrawable(R.drawable.ic_black_horse)
                }
                value = PieceValue.HORSE
            }
            8 -> {
                icon = if (isWhite) {
                    context.getDrawable(R.drawable.ic_white_tower)
                } else {
                    context.getDrawable(R.drawable.ic_black_tower)
                }
                value = PieceValue.TOWER
            }
        }
    }

    fun legalToMove(boardRect: BoardRectangle, board: GridLayout): Boolean {
        Log.d(CLASS_NAME, "legalToMove()", "Validating movement to a piece")
        when (value) {
            PieceValue.PAWN -> return pawnLegalToMove(boardRect, board)
            PieceValue.TOWER -> return towerLegalToMove(boardRect, board)
        }
        return true
    }

    private fun pawnLegalToMove(boardRect: BoardRectangle, board: GridLayout): Boolean {
        Log.d(CLASS_NAME, "pawnLegalToMove()", "Board x: ${boardRect.x} piece xlocation: $xLocation" )
        if (xLocation == boardRect.x - 1 || xLocation == boardRect.x + 1 ) {
            if (color == ChessGame.PlayerColor.WHITE) {
                //Log.d(CLASS_NAME, "pawnLegalToMove()", "Board y: ${boardRect.y} piece ylocation: $yLocation" )
                if (boardRect.y + 1 == yLocation) {
                    if (boardRect.chessPiece?.color == ChessGame.PlayerColor.BLACK) {
                        return true
                    }
                    //Log.d(CLASS_NAME, "pawnLegalToMove()", "Board orientation: ${board.orientation}")
                }
            }
            if (color == ChessGame.PlayerColor.BLACK) {
                if (boardRect.y - 1 == yLocation) {
                    if (boardRect.chessPiece?.color == ChessGame.PlayerColor.WHITE) {
                        return true
                    }
                }
            }
        }
        if (xLocation != boardRect.x) {
            return false
        }
        Log.d(CLASS_NAME, "pawnLegalToMove()", "Board row: ${boardRect.y} piece ylocation: $yLocation" )
        if (color == ChessGame.PlayerColor.WHITE) {
            if (yLocation == 7) {
                if (boardRect.y == yLocation!! - 2 && boardRect.chessPiece == null) {
                    return true
                }
            }
            if (boardRect.y == yLocation!! - 1 && boardRect.chessPiece == null) {
                return true
            }
        }
        if (color == ChessGame.PlayerColor.BLACK) {
            if (yLocation == 2) {
                if (boardRect.y == yLocation!! + 2 && boardRect.chessPiece == null) {
                    return true
                }
            }
            if (boardRect.y == yLocation!! + 1 && boardRect.chessPiece == null) {
                return true
            }
        }

        return false
    }

    fun moveTo(boardRect: BoardRectangle) {
        xLocation = boardRect.x
        yLocation = boardRect.y
        Log.d(CLASS_NAME, "moveTo()", "Moved piece to x: $xLocation y: $yLocation")
    }

    private fun getRectFromBoard(board: GridLayout, x: Int, y: Int): BoardRectangle {
        //Log.d(CLASS_NAME, "getRectFromBoard()", "x: $x y: $y")
        val index = (y-1) * 8 + (x-1)
        //Log.d(CLASS_NAME, "getRectFromBoard()", "index: $index")
        return board[index] as BoardRectangle
    }

    private fun towerLegalToMove(boardRect: BoardRectangle, board: GridLayout): Boolean {
        if (boardRect.x != xLocation && boardRect.y != yLocation) {
            return false
        }
        // Vertical movement
        if (boardRect.x == xLocation) {
            Log.d(CLASS_NAME, "towerLegalToMove()", "Vertical movement of tower, board y: ${boardRect.y} piece y: $yLocation")
            var yCoordinate = minOf(boardRect.y, yLocation!!) + 1
            val yMax = maxOf(boardRect.y, yLocation!!)
            while (yCoordinate < yMax) {
                if (getRectFromBoard(board, boardRect.x, yCoordinate).chessPiece != null) {
                    Log.d(CLASS_NAME, "towerLegalToMove()", "There is a piece in middle of the movement")
                    Log.d(CLASS_NAME, "towerLegalToMove()", "yCoordinate: $yCoordinate yMax $yMax")
                    Log.d(CLASS_NAME, "towerLegalToMove()", "xCoordinate: ${boardRect.x} yCoordinate $yCoordinate")
                    Log.d(CLASS_NAME, "towerLegalToMove()", "Piece in the way value: ${getRectFromBoard(board, boardRect.x, yCoordinate).chessPiece?.value}")
                    return false
                }
                yCoordinate += 1
            }
            if (boardRect.chessPiece == null) {
                return true
            }
            if (boardRect.chessPiece?.color != color) {
                return true
            }
            Log.d(CLASS_NAME, "towerLegalToMove()", "Trying to move on top of own piece")
        }

        // Horizontal movement
        if (boardRect.y == yLocation) {
            var xCoordinate = minOf(boardRect.x, xLocation!!) + 1
            val xMax = maxOf(boardRect.x, xLocation!!)
            while (xCoordinate < xMax) {
                if (getRectFromBoard(board, xCoordinate, boardRect.y).chessPiece != null) {
                    return false
                }
                xCoordinate += 1
            }
            if (boardRect.chessPiece == null) {
                return true
            }
            if (boardRect.chessPiece?.color != color) {
                return true
            }
        }
        return false
    }
}