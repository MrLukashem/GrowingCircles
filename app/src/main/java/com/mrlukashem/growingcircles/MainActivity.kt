package com.mrlukashem.growingcircles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity(), ViewOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GameObserver(applicationContext, lifecycle, this)
    }

    override fun addView(view: View) {
        setContentView(view)
    }
}
