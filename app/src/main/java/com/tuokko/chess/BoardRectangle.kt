package com.tuokko.chess

import android.content.Context
import android.util.AttributeSet
import android.view.View

class BoardRectangle: View {
    var isWhite: Boolean = false
    var rowNumber: Int = 0
    var columnNumber: Int = 0

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

}