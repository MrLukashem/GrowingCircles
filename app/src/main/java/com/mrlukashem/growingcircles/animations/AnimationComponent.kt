package com.mrlukashem.growingcircles.animations

import com.mrlukashem.growingcircles.OnFrameObserver
import com.mrlukashem.growingcircles.gameobjects.ShapeObject


abstract class AnimationComponent : OnFrameObserver, Drawable {

    var attachedObject: ShapeObject? = null

    fun attachTo(shapeObject: ShapeObject) {
        attachedObject = shapeObject
    }

    fun isAttachedTo(shapeObject: ShapeObject): Boolean = (attachedObject === shapeObject)
}