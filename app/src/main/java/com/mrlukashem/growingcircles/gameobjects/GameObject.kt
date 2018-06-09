package com.mrlukashem.growingcircles.gameobjects


interface GameObject {
    fun move(x: Double, y: Double)
    fun updateSize(percentage: Double)
}