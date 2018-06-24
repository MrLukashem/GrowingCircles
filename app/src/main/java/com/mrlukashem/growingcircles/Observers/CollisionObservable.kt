package com.mrlukashem.growingcircles.Observers


interface CollisionObservable<T> : OnFrameObserver {
    fun registerObserver(observer: CollisionObserver<T>)
    fun unregisterObserver(observer: CollisionObserver<T>)
}