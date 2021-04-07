package ru.doubletapp.eduapp.habits.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.databinding.FragmentHabitsListBinding
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.data.repository.MockRepository
import ru.doubletapp.eduapp.habits.ui.adapter.HabitAdapter

class HabitsListFragment : Fragment() {

    private val binding: FragmentHabitsListBinding by viewBinding()
    private val habitItems: List<Habit> by lazy {
        MockRepository.list
    }
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habits_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // requireActivity().setSupportActionBar(binding.habitsListToolbar)
        createAddButtonVisibilityMode()
        binding.addFabButton.setOnClickListener { addHabitButtonClick() }
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    fun addHabitButtonClick() {
        val intent = Intent(requireActivity(), HabitCreatorFragment::class.java)
        startActivity(intent)
    }

    private fun showData() {
        binding.habitsRecyclerView.layoutManager = linearLayoutManager
        val adapter = HabitAdapter(
            { checkView ->
                checkView.isSelected = !checkView.isSelected
            }, { _, position ->
                openHabitForEditing(position)
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

    private fun openHabitForEditing(position: Int) {
        val intent = Intent(requireActivity(), HabitCreatorFragment::class.java).apply {
            putExtra(HABIT_EXTRA_KEY, habitItems[position])
            putExtra(POSITION_KEY, position)
        }
        startActivity(intent)
    }

    companion object {
        const val ADD_BUTTON_VISIBILITY_MARK = 4
        const val HABIT_EXTRA_KEY = "habit_extra_key"
        const val POSITION_KEY = "position_key"
    }
}