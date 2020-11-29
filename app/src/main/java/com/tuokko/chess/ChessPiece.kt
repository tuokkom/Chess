package com.tuokko.chess

class ChessPiece(xInitial: Int, yInitial: Int) {

    enum class PieceColor {
        WHITE, BLACK
    }
    enum class PieceValue {
        PAWN, TOWER, HORSE, BISHOP, KING, QUEEN
    }
    var color: PieceColor? = null
    var value: PieceValue? = null
    var xLocation: Int? = null
    var yLocation: Int? = null

    init {
        xLocation = xInitial
        yLocation = yInitial
        when (yInitial) {
            1 -> {
                color = PieceColor.BLACK
                initPieceValue(xInitial)
            }
            2 -> {
                color = PieceColor.BLACK
                value = PieceValue.PAWN
            }
            7 -> {
                color = PieceColor.WHITE
                value = PieceValue.PAWN
            }
            8 -> {
                color = PieceColor.WHITE
                initPieceValue(xInitial)
            }
        }
    }

    private fun initPieceValue(xInitial: Int) {
        when (xInitial) {
            1 -> value = PieceValue.TOWER
            2 -> value = PieceValue.HORSE
            3 -> value = PieceValue.BISHOP
            4 -> value = PieceValue.QUEEN
            5 -> value = PieceValue.KING
            6 -> value = PieceValue.BISHOP
            7 -> value = PieceValue.HORSE
            8 -> value = PieceValue.TOWER
        }
    }

}