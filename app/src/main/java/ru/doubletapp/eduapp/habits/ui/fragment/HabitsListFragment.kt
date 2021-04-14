package ru.doubletapp.eduapp.habits.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.doubletapp.eduapp.habits.R
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.databinding.FragmentHabitsListBinding
import ru.doubletapp.eduapp.habits.ui.adapter.HabitAdapter

class HabitsListFragment : Fragment() {

    private val binding: FragmentHabitsListBinding by viewBinding()
    lateinit var habitItems: List<Habit>
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habits_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAddButtonVisibilityMode()
        addHabitButtonClick()
        addToggleToNavigationDrawer()
        habitItems = requireArguments().getParcelableArrayList(ITEMS_LIST_EXTRA) ?: ArrayList()
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun addHabitButtonClick() {
        binding.addFabButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_viewPagerContainerFragment_to_habitCreatorFragment, null)
        }
    }

    private fun showData() {
        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.habitsRecyclerView.layoutManager = layoutManager
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

    private fun createAddButtonVisibilityMode() {
        binding.habitsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                if (habitItems.size > ADD_BUTTON_VISIBILITY_MARK && lastPosition == habitItems.size - 1) {
                    binding.addFabButton.visibility = View.GONE
                } else binding.addFabButton.visibility = View.VISIBLE
            }
        }
        )
    }

    private fun openHabitForEditing(position: Int) {
        val bundle = Bundle().apply {
            putParcelable(HABIT_EXTRA_KEY, habitItems[position])
            putInt(POSITION_KEY, position)
        }
        findNavController().navigate(R.id.action_viewPagerContainerFragment_to_habitCreatorFragment, bundle)
    }

    private fun addToggleToNavigationDrawer() {
        val drawer = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            requireActivity(), drawer, requireActivity().findViewById(R.id.habitsListToolbar),
            R.string.navigation_open, R.string.navigation_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    companion object {
        const val ADD_BUTTON_VISIBILITY_MARK = 4
        const val HABIT_EXTRA_KEY = "habit_extra_key"
        const val POSITION_KEY = "position_key"
        const val ITEMS_LIST_EXTRA = "items_list_extra"

        fun newInstance(items: ArrayList<Habit>): HabitsListFragment =
            HabitsListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ITEMS_LIST_EXTRA, items)
                }
            }
    }
}