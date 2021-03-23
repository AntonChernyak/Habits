package ru.doubletapp.eduapp.habits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isNotIntent = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(FIRST_ACTIVITY_TAG, "onCreate()")
        counterTextView.text = "0"
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

        val number = counterTextView.text.toString().toInt()
        if (isNotIntent) outState.putInt(COUNTER_KEY, number + 1)
        else outState.putInt(COUNTER_KEY, number)

        isNotIntent = true
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(FIRST_ACTIVITY_TAG, "onRestoreInstanceState()")
        counterTextView.text = savedInstanceState.getInt(COUNTER_KEY).toString()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(FIRST_ACTIVITY_TAG, "onRestart()")
    }

    fun openSecondActivityOnClick(view: View) {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra(COUNTER_KEY, counterTextView.text.toString())
        isNotIntent = false
        startActivity(intent)
    }

    companion object {
        const val FIRST_ACTIVITY_TAG = "tag activity first"
        const val COUNTER_KEY = "counter key"
    }
}