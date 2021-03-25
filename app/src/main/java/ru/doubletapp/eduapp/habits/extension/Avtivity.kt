package ru.doubletapp.eduapp.habits.extension

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager
import androidx.annotation.Px
import kotlin.math.roundToInt

fun Activity.hideKeyboard(){
    this.currentFocus?.let { view ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

@Px
fun Context.spToPx(sp: Float): Int {
    return spToPx(sp, resources)
}

@Px
fun Context.dpToPx(dp: Float): Int {
    return dpToPx(dp, resources)
}

@Px
fun spToPx(sp: Float, resources: Resources): Int {
    return (sp * resources.displayMetrics.scaledDensity).roundToInt()
}

@Px
fun dpToPx(dp: Float, resources: Resources): Int {
    return (dp * resources.displayMetrics.density).roundToInt()
}