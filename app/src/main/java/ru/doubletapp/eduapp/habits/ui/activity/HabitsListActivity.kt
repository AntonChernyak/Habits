package ru.doubletapp.eduapp.habits.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.data.repository.MockRepository
import ru.doubletapp.eduapp.habits.databinding.ActivityHabitsListBinding
import ru.doubletapp.eduapp.habits.ui.adapter.HabitAdapter

class HabitsListActivity : AppCompatActivity() {

    private val binding: ActivityHabitsListBinding by viewBinding()
    private val habitItems: MutableList<Habit> by lazy {
        MockRepository.list
    }
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits_list)
        setSupportActionBar(binding.habitsListToolbar)
        showData()
        createAddButtonVisibilityMode()
    }

    override fun onResume() {
        super.onResume()
        binding.habitsRecyclerView.adapter?.notifyDataSetChanged()
    }

    fun addHabitButtonClick(view: View) {
        val intent = Intent(this, HabitCreatorActivity::class.java)
        startActivity(intent)
    }

    private fun showData() {
        binding.habitsRecyclerView.layoutManager = linearLayoutManager
        val adapter = HabitAdapter(
            { checkView ->
                checkView.isSelected = !checkView.isSelected
            }, {
                val intent = Intent(this, HabitCreatorActivity::class.java)
                startActivity(intent)
            }
        )

        adapter.habitsList = habitItems
        binding.habitsRecyclerView.adapter = adapter
    }

    private fun createAddButtonVisibilityMode(){
        binding.habitsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                if (habitItems.size > ADD_BUTTON_VISIBILITY_MARK && lastPosition == habitItems.size-1) {
                    binding.addFabButton.visibility = View.GONE
                } else binding.addFabButton.visibility = View.VISIBLE
            }
        }
        )
    }

    companion object {
        const val ADD_BUTTON_VISIBILITY_MARK = 4
    }
}