package ru.doubletapp.eduapp.habits.ui.activity

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.data.model.HabitTypeEnum
import ru.doubletapp.eduapp.habits.data.repository.MockRepository
import ru.doubletapp.eduapp.habits.databinding.ActivityHabitCreatorBinding
import ru.doubletapp.eduapp.habits.extension.hideKeyboard
import ru.doubletapp.eduapp.habits.ui.activity.HabitsListActivity.Companion.HABIT_EXTRA_KEY
import ru.doubletapp.eduapp.habits.ui.activity.HabitsListActivity.Companion.POSITION_KEY

class HabitCreatorActivity : AppCompatActivity() {

    private val binding: ActivityHabitCreatorBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_creator)
        createHabitPrioritySpinner()
        setDataFromIntent()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    fun createHabitButtonClick(view: View) {
        if (binding.habitTitleEditText.text.isNullOrEmpty() ||
            binding.periodDaysEditText.text.isNullOrEmpty() ||
            binding.periodTimesEditText.text.isNullOrEmpty()
        ) {
            fillInRequiredFields(view)
        } else {
            allRequiredDataEntered()
            val position = intent.getIntExtra(POSITION_KEY, DEFAULT_POSITION)
            val habit = createHabit()

            if (position == DEFAULT_POSITION) {
                MockRepository.addHabit(habit = habit)
                showCreateSnackbar(view)
            } else {
                replaceHabit(habit, position)
                showEditSnackBar(view, position)
            }
        }

        this.hideKeyboard()
    }

    private fun createHabit(): Habit {
        return Habit(
            title = binding.habitTitleEditText.text.toString(),
            description = binding.habitDescriptionEditText.text.toString(),
            priority = binding.prioritySpinner.selectedItem.toString(),
            type = getHabitType(),
            periodCount = binding.periodTimesEditText.text.toString(),
            periodDays = binding.periodDaysEditText.text.toString(),
            color = Color.parseColor("#EEEEEE")
        )
    }

    private fun replaceHabit(habit: Habit, position: Int) {
        MockRepository.list.removeAt(position)
        MockRepository.addHabit(position, habit)
    }

    private fun fillInRequiredFields(view: View) {
        Snackbar.make(view, getString(R.string.fill_in_required_fields), Snackbar.LENGTH_LONG).show()
        if (binding.periodTimesEditText.text.isNullOrEmpty()) {
            binding.periodTimesEditText.backgroundTintList = ColorStateList.valueOf(Color.RED)
        } else {
            binding.periodTimesEditText.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_color_green))
        }

        if (binding.periodDaysEditText.text.isNullOrEmpty()) {
            binding.periodDaysEditText.backgroundTintList = ColorStateList.valueOf(Color.RED)
        } else {
            binding.periodDaysEditText.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_color_green))
        }

        if (binding.habitTitleEditText.text.isNullOrEmpty()) {
            binding.habitTitleEditText.backgroundTintList = ColorStateList.valueOf(Color.RED)
        } else {
            binding.habitTitleEditText.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_color_green))
        }
    }

    private fun allRequiredDataEntered(){
        binding.habitTitleEditText.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_color_green))
        binding.periodDaysEditText.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_color_green))
        binding.periodTimesEditText.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_color_green))
    }

    private fun getHabitType(): HabitTypeEnum {
        val checkId = binding.habitTypesRadioGroup.checkedRadioButtonId
        return if (checkId == binding.goodHabitRadioButton.id) HabitTypeEnum.GOOD_HABIT
        else HabitTypeEnum.BAD_HABIT
    }

    private fun setHabitType(type: HabitTypeEnum) {
        if (type == HabitTypeEnum.GOOD_HABIT) binding.goodHabitRadioButton.isChecked = true
        else binding.badHabitRadioButton.isChecked = true
    }

    private fun createHabitPrioritySpinner() {
        val spinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.prioritySpinnerDataArray,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.prioritySpinner.adapter = spinnerAdapter
    }

    private fun showCreateSnackbar(view: View) {
        Snackbar.make(view, getString(R.string.habit_added), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.cancel)) {
                MockRepository.list.removeLast()
            }
            .setActionTextColor(ContextCompat.getColor(this, R.color.snackbar_action_color))
            .show()
    }

    private fun showEditSnackBar(view: View, position: Int) {
        Snackbar.make(view, getString(R.string.habit_edited), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.cancel)) {
                setDataFromIntent()
                val editingHabit = intent.getParcelableExtra<Habit>(HABIT_EXTRA_KEY)
                editingHabit?.let { habit -> replaceHabit(habit, position) }
            }
            .setActionTextColor(ContextCompat.getColor(this, R.color.snackbar_action_color))
            .show()
    }

    private fun setDataFromIntent() {
        val editingHabit = intent.getParcelableExtra<Habit>(HABIT_EXTRA_KEY)
        if (editingHabit != null) {
            binding.habitTitleEditText.setText(editingHabit.title)
            binding.habitDescriptionEditText.setText(editingHabit.description)
            binding.periodDaysEditText.setText(editingHabit.periodDays)
            binding.periodTimesEditText.setText(editingHabit.periodCount)
            binding.prioritySpinner.setSelection(editingHabit.priority.toInt() - 1)
            setHabitType(editingHabit.type)
        }
    }

    companion object {
        const val DEFAULT_POSITION = -1
    }
}