package com.mrlukashem.growingcircles.views

import android.graphics.Canvas
import com.mrlukashem.growingcircles.drawable.Drawable


class DrawableComposite: Drawable, MutableCollection<Drawable> {

    override val size: Int
        get() = drawables.size

    private val drawables: MutableList<Drawable> = mutableListOf()

    override fun contains(element: Drawable): Boolean = drawables.contains(element)

    override fun containsAll(elements: Collection<Drawable>): Boolean {
        return drawables.containsAll(elements)
    }

    override fun isEmpty(): Boolean = drawables.isEmpty()

    override fun add(element: Drawable): Boolean = drawables.add(element)

    override fun addAll(elements: Collection<Drawable>): Boolean = drawables.addAll(elements)

    override fun clear() = drawables.clear()

    override fun iterator(): MutableIterator<Drawable> = drawables.iterator()

    override fun remove(element: Drawable): Boolean = drawables.remove(element)

    override fun removeAll(elements: Collection<Drawable>): Boolean = drawables.removeAll(elements)

    override fun retainAll(elements: Collection<Drawable>): Boolean = drawables.retainAll(elements)

    override fun draw(canvas: Canvas) {
        drawables.forEach {
            it.draw(canvas)
        }
    }
}