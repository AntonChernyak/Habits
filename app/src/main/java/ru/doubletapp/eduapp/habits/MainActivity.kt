package ru.doubletapp.eduapp.habits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(FIRST_ACTIVITY_TAG, "onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(FIRST_ACTIVITY_TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(FIRST_ACTIVITY_TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(FIRST_ACTIVITY_TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(FIRST_ACTIVITY_TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(FIRST_ACTIVITY_TAG, "onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(FIRST_ACTIVITY_TAG, "onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(FIRST_ACTIVITY_TAG, "onRestoreInstanceState()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(FIRST_ACTIVITY_TAG, "onRestart()")
    }

    companion object {
        const val FIRST_ACTIVITY_TAG = "activity first"
    }
}