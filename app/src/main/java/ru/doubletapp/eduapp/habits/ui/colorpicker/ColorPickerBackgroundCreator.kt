package ru.doubletapp.eduapp.habits.ui.colorpicker

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.core.view.updateMargins
import androidx.recyclerview.widget.LinearLayoutManager
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.databinding.ActivityHabitCreatorBinding
import ru.doubletapp.eduapp.habits.extension.dpToPx
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
                (ITEM_COLOR_COUNT * 2) * context.resources.getDimension(R.dimen.color_picker_item_space_size)
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

    fun createColorPickerItems(context: Context, colorItemClick: (View) -> Unit){
        val bitmap = createBackgroundBitmap(context)
        val marginSpace = context.resources.getDimension(R.dimen.color_picker_item_space_size).roundToInt()
        val itemColorSize =  context.resources.getDimension(R.dimen.item_color_picker_size).roundToInt()

        val startPosition = marginSpace + 0.5 * itemColorSize
        val step = itemColorSize + 2 * marginSpace

        val itemsContainer = (context as Activity).findViewById<LinearLayout>(R.id.color_picker_container)

        for (item in 1..ITEM_COLOR_COUNT - 1) {

            Log.d("TAGGGG", "$item [px] widt = ${bitmap?.width?.toFloat()?.let { context.dpToPx(it) }}, x = ${context.dpToPx((startPosition + item * step).toFloat())}")
            Log.d("TAGGGG", "$item [dp] widt = ${bitmap?.width}, x = ${(startPosition + item * step).toFloat()}")

            val pixel = bitmap?.getPixel((startPosition + item * step).roundToInt(), 0)

            val colorItemView = View(context).apply {
                layoutParams = ViewGroup.MarginLayoutParams(
                    itemColorSize,
                    itemColorSize
                ).apply {
                    updateMargins(
                        right = marginSpace,
                        left = marginSpace
                    )
                }
                background = ContextCompat.getDrawable(this.context, R.drawable.selected_color_background)
                foreground = ContextCompat.getDrawable(this.context, R.drawable.selected_color_foreground)
                backgroundTintList = pixel?.let { ColorStateList.valueOf(it) }
                this.setOnClickListener(colorItemClick)
            }

            itemsContainer.addView(colorItemView)
        }
    }
}