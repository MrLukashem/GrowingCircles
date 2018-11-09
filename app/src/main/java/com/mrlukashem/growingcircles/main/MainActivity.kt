package com.mrlukashem.growingcircles.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import butterknife.BindView
import com.mrlukashem.growingcircles.R


class MainActivity : AppCompatActivity(), ViewOwner {

    private lateinit var buttonMode1: Button
    private lateinit var buttonMode2: Button
    private lateinit var buttonMode3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()

        setContentView(R.layout.activity_main)

        buttonMode1 = findViewById(R.id.button)
        buttonMode2 = findViewById(R.id.button2)
        buttonMode3 = findViewById(R.id.button3)

        buttonMode1.setOnClickListener {
            GameObserver(applicationContext, lifecycle, this)
        }
        buttonMode2.setOnClickListener {

        }
        buttonMode3.setOnClickListener {

        }
    }

    override fun addView(view: View) {
        setContentView(view)
    }
}
