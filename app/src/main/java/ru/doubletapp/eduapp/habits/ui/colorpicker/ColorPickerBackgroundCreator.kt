package ru.doubletapp.eduapp.habits.ui.colorpicker

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import ru.doubletapp.eduapp.habits.R

object ColorPickerBackgroundCreator {

    private const val ITEM_COLOR_COUNT = 16

    fun getColorBackgroundDrawable(context: Context): PaintDrawable {
        val sFactory: ShapeDrawable.ShaderFactory = object : ShapeDrawable.ShaderFactory() {
            override fun resize(width: Int, height: Int): Shader {
                return getShader(context)
            }
        }

        return PaintDrawable().apply {
            shape = RectShape()
            shaderFactory = sFactory
        }
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

    private fun getShader(context: Context): Shader{
        val width = ITEM_COLOR_COUNT * context.resources.getDimension(R.dimen.item_color_picker_size) +
                (ITEM_COLOR_COUNT * 2) * context.resources.getDimension(R.dimen.spacing_large_32)

        return LinearGradient(
            0f,
            0f,
            width,
            0f,
            buildHueColorArray(),
            null,
            Shader.TileMode.CLAMP
        )
    }

}