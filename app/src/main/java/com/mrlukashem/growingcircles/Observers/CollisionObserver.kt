package com.mrlukashem.growingcircles.Observers


interface CollisionObserver<T> {
    fun onCollision(firstObj: T, secondObj: T)
}
