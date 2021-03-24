package ru.doubletapp.eduapp.habits.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class HabitTypeEnum : Parcelable {
    GOOD_HABIT,
    BAD_HABIT
}