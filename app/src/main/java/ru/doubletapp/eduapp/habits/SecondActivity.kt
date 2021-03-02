package ru.doubletapp.eduapp.habits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_second.*
import ru.doubletapp.eduapp.habits.MainActivity.Companion.COUNTER_KEY

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d(SECOND_ACTIVITY_TAG, "onCreate()")

        val number = intent.getStringExtra(COUNTER_KEY)?.toInt()
        if (number != null) squareTextView.text = (number * number).toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d(SECOND_ACTIVITY_TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(SECOND_ACTIVITY_TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(SECOND_ACTIVITY_TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(SECOND_ACTIVITY_TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(SECOND_ACTIVITY_TAG, "onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(SECOND_ACTIVITY_TAG, "onSaveInstanceState()")

        outState.putString(SQUARE_KEY, squareTextView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(SECOND_ACTIVITY_TAG, "onRestoreInstanceState()")

        squareTextView.text = savedInstanceState.getString(SQUARE_KEY)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(SECOND_ACTIVITY_TAG, "onRestart()")
    }

    companion object {
        const val SECOND_ACTIVITY_TAG = "tag activity second"
        const val SQUARE_KEY = "square key"
    }
}