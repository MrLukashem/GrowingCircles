package com.mrlukashem.growingcircles.gameobjects

import com.mrlukashem.growingcircles.animations.AnimatedComponent


abstract class AnimatedShapeDecorator : AnimatedComponent {
    abstract val shape: Shape
}