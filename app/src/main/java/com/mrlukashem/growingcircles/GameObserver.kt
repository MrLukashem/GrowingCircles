package com.mrlukashem.growingcircles

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.view.Choreographer

import com.mrlukashem.growingcircles.views.GameView
import com.mrlukashem.mediacontentprovider.multithreading.TasksDispatcher


class GameObserver(
        private val mContext: Context,
        private val mLifeCycle: Lifecycle,
        private val mViewOwner: ViewOwner)
    : LifecycleObserver, Choreographer.FrameCallback, FrameDrawnObserver {

    private lateinit var mChoreographer: Choreographer
    private val mDispatcher = TasksDispatcher<Unit>()

    private var mGameView: GameView = GameView(mContext)

    init {
        mLifeCycle.addObserver(this)
        mGameView.registerObserver(this)

        mViewOwner.addView(mGameView)

        mDispatcher.begin()
        mDispatcher.dispatch({
            mChoreographer = Choreographer.getInstance()
        }, {
            mChoreographer.postFrameCallback(this)
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        // gameView = GameView(mContext)
        mViewOwner.addView(mGameView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mLifeCycle.removeObserver(this)
        mDispatcher.quit()
    }

    override fun doFrame(frameTimeNanos: Long) {
        mGameView.postInvalidate()
    }

    override fun onFrameDrawn() {
        mChoreographer.postFrameCallback(this)
    }
}