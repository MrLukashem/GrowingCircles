package com.mrlukashem.growingcircles.gameobjects

import com.mrlukashem.growingcircles.OnFrameObserver


interface ShapeObject : OnFrameObserver {

    val boundsHeight: Float
    val boundsWidth: Float
    val xPosition: Float
    val yPosition: Float

    fun move(x: Float, y: Float)
    fun hasCollisionWith(shapeObject: ShapeObject)
}

fun ShapeObject.toDisplaySpace(): ShapeObject = this

fun ShapeObject.fromDisplaySpace(): ShapeObject = this