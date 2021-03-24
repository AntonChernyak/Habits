package ru.doubletapp.eduapp.habits.data.model

import android.graphics.Color
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Habit(
    val title: String,
    val description: String = "",
    val priority: String,
    val type: HabitTypeEnum = HabitTypeEnum.GOOD_HABIT,
    val periodCount: String,
    val periodDays: String,
    val color: Int  = Color.WHITE
) : Parcelable