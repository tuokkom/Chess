package com.tuokko.chess

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.view.View

class BoardRectangle: View {
    private val CLASS_NAME = "BoardRectangle"

    var isWhite: Boolean = false
    var rowNumber: Int = 0
    var columnNumber: Int = 0
    var chessPiece: ChessPiece? = null
    var boardBackground: Drawable? = null

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle) {
        init()
    }

    private fun init() {

    }

    fun makeGray() {
        if (chessPiece != null) {
            Log.d(CLASS_NAME, "makeGray()", "Board piece has a piece on it")
            val icon = chessPiece?.icon
            if (icon == null || boardBackground == null) {
                return
            }
            val transparentLayer = context.getDrawable(R.drawable.board_background_chosen)
            val layers = arrayOf(boardBackground!!, transparentLayer!!, icon)
            val layerDrawable = LayerDrawable(layers)
            background = layerDrawable
        }
    }

    fun removeGray() {
        drawBoardRectangle()
    }

    fun moveTo(moveToPart: BoardRectangle): Boolean {
        if (chessPiece == null) {
            return false
        }
        if (chessPiece!!.legalToMove(moveToPart)) {
            moveToPart.addChessPiece(chessPiece)
            chessPiece = null
            drawBoardRectangle()
            return true
        }
        return false
    }

    fun addChessPiece(piece: ChessPiece?) {
        chessPiece = piece
        drawBoardRectangle()
    }

    fun drawBoardRectangle() {
        if (boardBackground == null) {
            return
        }
        val icon: Drawable? = chessPiece?.icon

        val layers = if (icon != null) {
            arrayOf(boardBackground!!, icon)
        } else {
            arrayOf(boardBackground!!)
        }

        val layerDrawable = LayerDrawable(layers)
        background = layerDrawable
    }
}