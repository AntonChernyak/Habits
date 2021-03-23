package ru.doubletapp.eduapp.habits.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.data.repository.MockRepository
import ru.doubletapp.eduapp.habits.databinding.ActivityHabitsListBinding
import ru.doubletapp.eduapp.habits.ui.adapter.HabitAdapter

class HabitsListActivity : AppCompatActivity() {

    private val binding: ActivityHabitsListBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits_list)
        setSupportActionBar(binding.habitsListToolbar)
        showData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    fun addHabitButtonClick(view: View) {
        val intent = Intent(this, HabitCreatorActivity::class.java)
        startActivity(intent)
    }

    private fun showData() {
        val adapter = HabitAdapter(
            { checkView ->
                checkView.isSelected = !checkView.isSelected
            }, {
                val intent = Intent(this, HabitCreatorActivity::class.java)
                startActivity(intent)
            }
        )
        binding.habitsRecyclerView.adapter = adapter
        adapter.habitsList = MockRepository.getHabits()
    }

    companion object {
    }
}