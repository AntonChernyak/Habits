package ru.doubletapp.eduapp.habits.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.data.model.HabitTypeEnum
import ru.doubletapp.eduapp.habits.data.repository.MockRepository
import ru.doubletapp.eduapp.habits.ui.fragment.HabitsListFragment

class HabitViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val positiveHabits: List<Habit> by lazy {
        MockRepository.list.filter { it.type == HabitTypeEnum.GOOD_HABIT }
    }

    override fun getItemCount(): Int = TAB_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            POSITIVE_FRAGMENT_POSITION -> HabitsListFragment()
            NEGATIVE_FRAGMENT_POSITION -> HabitsListFragment()
            else -> HabitsListFragment(emptyList())
        }
    }

    companion object {
        private const val TAB_COUNT = 2
        private const val POSITIVE_FRAGMENT_POSITION = 0
        private const val NEGATIVE_FRAGMENT_POSITION = 1
    }
}