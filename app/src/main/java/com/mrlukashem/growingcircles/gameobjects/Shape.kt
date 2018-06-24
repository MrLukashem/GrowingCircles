package com.mrlukashem.growingcircles.gameobjects

import android.graphics.PointF


interface Shape {

    var boundsRadius: Float
    var position: PointF

    fun move(x: Float, y: Float)
    fun hasCollisionWith(shape: Shape): Boolean
    fun contains(x: Float, y: Float): Boolean
}

fun Shape.toDisplaySpace(): Shape = this

fun Shape.fromDisplaySpace(): Shape = this