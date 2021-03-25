package ru.doubletapp.eduapp.habits.extension

import android.graphics.Color
import android.view.View

fun View.getBackgroundColor(): Int {
    return backgroundTintList?.defaultColor ?: Color.MAGENTA
}