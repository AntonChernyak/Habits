package ru.doubletapp.eduapp.habits.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.doubletapp.eduapp.habits.R

class HabitsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits_list)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    companion object {
    }
}