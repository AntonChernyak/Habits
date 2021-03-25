package ru.doubletapp.eduapp.habits.ui.colorpicker

import android.content.Context
import android.graphics.*
import ru.doubletapp.eduapp.habits.R
import kotlin.math.roundToInt


object ColorPickerBackgroundCreator {

    private const val ITEM_COLOR_COUNT = 16

    fun createBackgroundBitmap(context: Context): Bitmap? {
        val bitmap = Bitmap.createBitmap(getWidth(context).roundToInt(), 1, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawRect(RectF(0f, 0f, getWidth(context), 1f), getBackgroundDrawable(context))
        return bitmap
    }

    private fun buildHueColorArray(): IntArray {
        val hue = IntArray(361)
        var count = 0
        var i = hue.size - 1
        while (i >= 0) {
            hue[count] = Color.HSVToColor(floatArrayOf(i.toFloat(), 1f, 1f))
            i--
            count++
        }
        return hue
    }

    private fun getWidth(context: Context): Float{
        return ITEM_COLOR_COUNT * context.resources.getDimension(R.dimen.item_color_picker_size) +
                (ITEM_COLOR_COUNT * 2) * context.resources.getDimension(R.dimen.spacing_large_32)
    }

    private fun getBackgroundDrawable(context: Context): Paint{
        val gradient = LinearGradient(
            0f,
            0f,
            getWidth(context),
            0f,
            buildHueColorArray(),
            null,
            Shader.TileMode.CLAMP)
        return Paint().apply {
            isDither = true
            shader = gradient
        }

    }
}