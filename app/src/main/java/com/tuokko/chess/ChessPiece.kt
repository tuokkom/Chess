package com.tuokko.chess

import android.content.Context
import android.graphics.drawable.Drawable

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

    fun legalToMove(boardRect: BoardRectangle): Boolean {
        Log.d(CLASS_NAME, "legalToMove()", "Validating movement to a piece")
        when (value) {
            PieceValue.PAWN -> return pawnLegalToMove(boardRect)
        }
        return true
    }

    private fun pawnLegalToMove(boardRect: BoardRectangle): Boolean {
        Log.d(CLASS_NAME, "pawnLegalToMove()", "Board column: ${boardRect.columnNumber} piece xlocation: $xLocation" )
        if (xLocation != boardRect.columnNumber) {
            return false
        }
        Log.d(CLASS_NAME, "pawnLegalToMove()", "Board row: ${boardRect.rowNumber} piece ylocation: $yLocation" )
        if (color == ChessGame.PlayerColor.WHITE) {
            if (yLocation == 7) {
                if (boardRect.rowNumber == yLocation!! - 2) {
                    return true
                }
            }
            if (boardRect.rowNumber == yLocation!! - 1) {
                return true
            }
        }
        if (color == ChessGame.PlayerColor.BLACK) {
            if (yLocation == 2) {
                if (boardRect.rowNumber == yLocation!! + 2) {
                    return true
                }
            }
            if (boardRect.rowNumber == yLocation!! + 1) {
                return true
            }
        }

        return false
    }

    fun moveTo(boardRect: BoardRectangle) {
        xLocation = boardRect.columnNumber
        yLocation = boardRect.rowNumber
        Log.d(CLASS_NAME, "moveTo()", "Moved piece to x: $xLocation y: $yLocation")
    }
}