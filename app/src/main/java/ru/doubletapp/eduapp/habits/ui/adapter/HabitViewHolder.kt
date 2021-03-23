package ru.doubletapp.eduapp.habits.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.databinding.ItemHabitBinding

class HabitViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    private val binding = ItemHabitBinding.bind(itemView)

    fun bind(habit: Habit, click: (View) -> Unit) {
        binding.habitTitleTextView.text = habit.title
        binding.habitDescriptionTextView.text = habit.description
        binding.habitColorLabel.setBackgroundColor(habit.color)
        binding.periodTextView.text = createPeriodString(habit)
        binding.priorityTextView.text = habit.priority.toString()

        binding.checkImageView.setOnClickListener(click)
    }

    private fun createPeriodString(habit: Habit): String{
        return itemView.resources.getString(R.string.period_string, habit.periodCount, habit.periodDays)
    }
}