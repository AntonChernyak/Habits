package ru.doubletapp.eduapp.habits.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.data.model.HabitTypeEnum
import ru.doubletapp.eduapp.habits.data.repository.MockRepository
import ru.doubletapp.eduapp.habits.databinding.ActivityHabitCreatorBinding

class HabitCreatorActivity : AppCompatActivity() {

    private val binding: ActivityHabitCreatorBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_creator)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }

    fun createHabitButtonClick(view: View) {
        MockRepository.addHabit(createHabit())
    }

    private fun createHabit(): Habit{
        return Habit(
            title = binding.habitNameEditText.text.toString(),
            description = binding.habitDescriptionEditText.text.toString(),
            priority = 0,
            type = getHabitType(),
            periodCount = binding.periodTimesEditText.text.toString(),
            periodDays = binding.periodDaysEditText.text.toString(),
            color = Color.parseColor("#EEEEEE")
            )
    }

    private fun getHabitType() : HabitTypeEnum {
        val checkId = binding.habitTypesRadioGroup.checkedRadioButtonId
        return if (checkId == binding.goodHabitRadioButton.id) HabitTypeEnum.GOOD_HABIT
        else HabitTypeEnum.BAD_HABIT
    }

    companion object {

    }
}