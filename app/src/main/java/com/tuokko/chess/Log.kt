package com.tuokko.chess

class Log {

    companion object {
        private val PACKAGE_NAME = "Chess"

        fun d(CLASS_NAME: String, METHOD_NAME: String, MSG: String) {
            android.util.Log.d(PACKAGE_NAME, "$CLASS_NAME $METHOD_NAME $MSG")
        }
    }

}