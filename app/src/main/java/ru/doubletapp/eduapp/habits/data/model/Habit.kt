package ru.doubletapp.eduapp.habits.data.model

import android.graphics.Color

data class Habit(
    val title: String,
    val description: String? = "",
    val priority: Int? = 0,
    val type: HabitTypeEnum = HabitTypeEnum.GOOD_HABIT,
    val periodCount: String,
    val periodDays: String,
    val color: Int  = Color.WHITE
)