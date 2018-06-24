package com.mrlukashem.growingcircles.main

import com.mrlukashem.growingcircles.Observers.OnFrameObservable
import com.mrlukashem.growingcircles.Observers.OnFrameObserver
import com.mrlukashem.growingcircles.drawable.DrawableShape
import com.mrlukashem.growingcircles.gameobjects.DrawableShapesFactory
import com.mrlukashem.growingcircles.gameobjects.DrawableShapesFactory.*
import com.mrlukashem.growingcircles.gameobjects.RandomDrawableShapesFactory
import com.mrlukashem.growingcircles.views.SpaceManager


class DrawableShapesController(onFrameObservable: OnFrameObservable, spaceManager: SpaceManager)
    : OnFrameObserver {

    private var onDestroyCallback: ((DrawableShape) -> Boolean)? = null
    private var onCreateCallback: ((DrawableShape) -> DrawableShape)? = null
    private val storage: MutableList<DrawableShape> = mutableListOf()
    private val shapesFactory: DrawableShapesFactory =
            RandomDrawableShapesFactory(storage, spaceManager.gameDisplay)

    init {
        onFrameObservable.registerObserver(this)
    }

    fun createShape(type: ShapeType): DrawableShape {
        var newShape = shapesFactory.create(type)
        onCreateCallback?.invoke(newShape)?.apply {
            newShape = this
        }

        storage.add(newShape)
        return storage.last()
    }

    fun createFewShapes(numOfObjects: Int, type: ShapeType): List<DrawableShape> {
        val createdShapes: MutableList<DrawableShape> = mutableListOf()
        for (i in 1..numOfObjects) {
            var newShape = shapesFactory.create(type)
            onCreateCallback?.invoke(newShape)?.apply {
                newShape = this
            }
            createdShapes.add(newShape)
        }

        storage.addAll(createdShapes)
        return createdShapes
    }

    fun destroyShape(shape: DrawableShape) {
        onDestroyCallback?.invoke(shape)?.apply {
            if (this) storage.remove(shape)
        }
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        storage.forEach {
            it.onFrameOccurred(frameTimeMillis, deltaTimeMillis)
        }
    }

    fun clear() = storage.clear()

    fun registerOnDestroyCallback(callback: (DrawableShape) -> Boolean) {
        onDestroyCallback = callback
    }

    fun registerOnCreateCallback(callback: (DrawableShape) -> DrawableShape) {
        onCreateCallback = callback
    }
}