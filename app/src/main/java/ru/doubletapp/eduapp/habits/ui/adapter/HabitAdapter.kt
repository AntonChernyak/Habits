package ru.doubletapp.eduapp.habits.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.data.model.Habit

class HabitAdapter(
    private val checkClick: (View) -> Unit,
    private val itemClick: (View, Int) -> Unit
): RecyclerView.Adapter<HabitViewHolder>() {

    var habitsList: List<Habit> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitsList[position]
        holder.itemView.setOnClickListener { itemClick.invoke(holder.itemView, position)}
        holder.bind(habit, checkClick)
    }

    override fun getItemCount(): Int = habitsList.size
}