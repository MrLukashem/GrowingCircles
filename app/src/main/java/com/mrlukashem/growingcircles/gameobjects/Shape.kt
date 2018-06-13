package com.mrlukashem.growingcircles.gameobjects


interface Shape {

    val boundsHeight: Float
    val boundsWidth: Float
    var xPosition: Float
    var yPosition: Float

    fun move(x: Float, y: Float)
    fun hasCollisionWith(shape: Shape)
}

fun Shape.toDisplaySpace(): Shape = this

fun Shape.fromDisplaySpace(): Shape = this