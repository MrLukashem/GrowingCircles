package com.mrlukashem.growingcircles.main

interface GameInstanceObservable {
    fun register(observer: GameInstanceObserver)
    fun unregister(observer: GameInstanceObserver)
}