package com.mrlukashem.growingcircles.drawable

import com.mrlukashem.growingcircles.Observers.OnFrameObserver
import com.mrlukashem.growingcircles.gameobjects.Shape


interface DrawableShape : Shape, Drawable, OnFrameObserver {
}