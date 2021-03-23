package ru.doubletapp.eduapp.habits.data.repository

import android.graphics.Color
import ru.doubletapp.eduapp.habits.data.model.Habit

object MockRepository {

    fun getHabits(): List<Habit>{
        val firstHabit = Habit(
            title = "Погладить кота",
            priority = 4,
            periodCount = 3,
            periodDays = 1,
            color = Color.RED
        )

        val secondHabit = Habit(
            title = "Покормить кота",
            priority = 5,
            periodCount = 4,
            periodDays = 1,
            color = Color.MAGENTA
        )

        return listOf(firstHabit, secondHabit)
    }
}