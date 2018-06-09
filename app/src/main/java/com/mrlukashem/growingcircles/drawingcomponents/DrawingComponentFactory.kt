package com.mrlukashem.growingcircles.drawingcomponents

import com.mrlukashem.growingcircles.gameobjects.GameObject


interface DrawingComponentFactory {
    fun create(gameObject: GameObject): DrawingComponent
}