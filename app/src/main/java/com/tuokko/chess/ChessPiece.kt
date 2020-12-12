package com.tuokko.chess

import android.content.Context
import android.graphics.drawable.Drawable

class ChessPiece(xInitial: Int, yInitial: Int, ctx: Context) {

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

    fun legalToMove(boardPiece: BoardRectangle): Boolean {
        return true
    }

}