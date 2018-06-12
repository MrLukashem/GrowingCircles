package com.mrlukashem.growingcircles

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager


class SpaceConverter(private val context: Context) {

    val gameDisplay: Display = gameDisplay()
    val deviceDisplay: Display = defaultDisplay()

    private fun windowService(): WindowManager {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    private fun defaultDisplay(): Display {
        return windowService().defaultDisplay
    }

    private fun gameDisplay(): Display {
        return windowService().defaultDisplay
    }
}

fun Point.toDisplaySpace() = this

fun Point.toGameSpace() = this