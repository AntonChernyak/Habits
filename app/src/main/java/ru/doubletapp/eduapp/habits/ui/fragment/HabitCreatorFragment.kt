package ru.doubletapp.eduapp.habits.ui.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.doubletapp.eduapp.habits.R
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.data.model.HabitTypeEnum
import ru.doubletapp.eduapp.habits.data.repository.MockRepository
import ru.doubletapp.eduapp.habits.extension.hideKeyboard
import ru.doubletapp.eduapp.habits.ui.colorpicker.ColorPickerCreator
import ru.doubletapp.eduapp.habits.databinding.FragmentHabitCreatorBinding
import ru.doubletapp.eduapp.habits.extension.getBackgroundColor
import ru.doubletapp.eduapp.habits.ui.fragment.HabitsListFragment.Companion.HABIT_EXTRA_KEY

class HabitCreatorFragment : Fragment() {

    private val binding: FragmentHabitCreatorBinding by viewBinding()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habit_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createHabitPrioritySpinner()
        setDataFromIntent()
        setColorPicker()
        binding.createHabitButton.setOnClickListener { createHabitButtonClick() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putString(TITLE_KEY, binding.habitTitleEditText.text.toString())
            putString(DESCRIPTION_KEY, binding.habitDescriptionEditText.text.toString())
            putString(PRIORITY_KEY, binding.prioritySpinner.selectedItem.toString())
            putString(PERIOD_COUNT_KEY, binding.periodTimesEditText.text.toString())
            putString(PERIOD_DAYS_KEY, binding.periodDaysEditText.text.toString())
            putParcelable(TYPE_KEY, getHabitType())
            putInt(COLOR_KEY, binding.selectedColorView.getBackgroundColor())
        }
    }

/*
        override fun onRestoreInstanceState(savedInstanceState: Bundle) {
            super.onRestoreInstanceState(savedInstanceState)
            binding.habitTitleEditText.setText(savedInstanceState.getString(TITLE_KEY))
            binding.habitDescriptionEditText.setText(savedInstanceState.getString(DESCRIPTION_KEY))
            binding.periodDaysEditText.setText(savedInstanceState.getString(PERIOD_DAYS_KEY))
            binding.periodTimesEditText.setText(savedInstanceState.getString(PERIOD_COUNT_KEY))
            savedInstanceState.getString(PRIORITY_KEY)?.toInt()
                ?.minus(1)?.let { binding.prioritySpinner.setSelection(it) }
            savedInstanceState.getParcelable<HabitTypeEnum>(TYPE_KEY)?.let { setHabitType(it) }
            binding.selectedColorView.backgroundTintList =
                ColorStateList.valueOf(savedInstanceState.getInt(COLOR_KEY, Color.GRAY))
        }
*/

    private fun createHabitButtonClick() {
        if (binding.habitTitleEditText.text.isNullOrEmpty() ||
            binding.periodDaysEditText.text.isNullOrEmpty() ||
            binding.periodTimesEditText.text.isNullOrEmpty()
        ) {
            fillInRequiredFields()
        } else {
            allRequiredDataEntered()
            val position = requireActivity().intent.getIntExtra(POSITION_KEY, DEFAULT_POSITION)
            val habit = createHabit()

            if (position == DEFAULT_POSITION) {
                MockRepository.addHabit(habit = habit)
                showCreateSnackbar()
            } else {
                replaceHabit(habit, position)
                showEditSnackBar(position)
            }
        }

        requireActivity().hideKeyboard()
    }

    private fun createHabit(): Habit {
        return Habit(
            title = binding.habitTitleEditText.text.toString(),
            description = binding.habitDescriptionEditText.text.toString(),
            priority = binding.prioritySpinner.selectedItem.toString(),
            type = getHabitType(),
            periodCount = binding.periodTimesEditText.text.toString(),
            periodDays = binding.periodDaysEditText.text.toString(),
            color = binding.selectedColorView.getBackgroundColor()
        )
    }

    private fun replaceHabit(habit: Habit, position: Int) {
        MockRepository.list.removeAt(position)
        MockRepository.addHabit(position, habit)
    }

    private fun fillInRequiredFields() {
        Snackbar.make(binding.root, getString(R.string.fill_in_required_fields), Snackbar.LENGTH_LONG)
            .show()
        if (binding.periodTimesEditText.text.isNullOrEmpty()) {
            binding.periodTimesEditText.backgroundTintList = ColorStateList.valueOf(Color.RED)
        } else {
            binding.periodTimesEditText.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.primary_color_green
                    )
                )
        }

        if (binding.periodDaysEditText.text.isNullOrEmpty()) {
            binding.periodDaysEditText.backgroundTintList = ColorStateList.valueOf(Color.RED)
        } else {
            binding.periodDaysEditText.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.primary_color_green
                    )
                )
        }

        if (binding.habitTitleEditText.text.isNullOrEmpty()) {
            binding.habitTitleEditText.backgroundTintList = ColorStateList.valueOf(Color.RED)
        } else {
            binding.habitTitleEditText.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.primary_color_green
                    )
                )
        }
    }

    private fun allRequiredDataEntered() {
        binding.habitTitleEditText.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.primary_color_green
                )
            )
        binding.periodDaysEditText.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.primary_color_green
                )
            )
        binding.periodTimesEditText.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.primary_color_green
                )
            )
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
            requireActivity(),
            R.array.prioritySpinnerDataArray,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.prioritySpinner.adapter = spinnerAdapter
    }

    private fun showCreateSnackbar() {
        Snackbar.make(binding.root, getString(R.string.habit_added), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.cancel)) {
                MockRepository.list.removeLast()
            }
            .setActionTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.snackbar_action_color
                )
            )
            .show()
    }

    private fun showEditSnackBar(position: Int) {
        Snackbar.make(binding.root, getString(R.string.habit_edited), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.cancel)) {
                setDataFromIntent()
                val editingHabit =
                    requireActivity().intent.getParcelableExtra<Habit>(HABIT_EXTRA_KEY)
                editingHabit?.let { habit -> replaceHabit(habit, position) }
            }
            .setActionTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.snackbar_action_color
                )
            )
            .show()
    }

    private fun setDataFromIntent() {
        val editingHabit = requireActivity().intent.getParcelableExtra<Habit>(HABIT_EXTRA_KEY)
        if (editingHabit != null) {
            binding.habitTitleEditText.setText(editingHabit.title)
            binding.habitDescriptionEditText.setText(editingHabit.description)
            binding.periodDaysEditText.setText(editingHabit.periodDays)
            binding.periodTimesEditText.setText(editingHabit.periodCount)
            binding.prioritySpinner.setSelection(editingHabit.priority.toInt() - 1)
            setHabitType(editingHabit.type)
            binding.selectedColorView.backgroundTintList =
                ColorStateList.valueOf(editingHabit.color)
        }
    }

    private fun setColorPicker() {
        binding.selectedColorView.setOnClickListener {
            val scrollViewVisibility = binding.colorScrollView.colorPickerScrollView.visibility
            binding.colorScrollView.colorPickerScrollView.visibility =
                if (scrollViewVisibility == View.GONE) View.VISIBLE
                else View.GONE
        }

        binding.colorScrollView.colorPickerContainer.background = BitmapDrawable(
            resources,
            ColorPickerCreator.createBackgroundBitmap(requireActivity())
        )

        ColorPickerCreator.createColorPickerItems(requireActivity()) {
            val color = it.backgroundTintList
            binding.selectedColorView.backgroundTintList = color
            binding.colorScrollView.colorPickerScrollView.visibility = View.GONE
        }
    }

    companion object {
        const val DEFAULT_POSITION = -1
        const val TITLE_KEY = "title_key"
        const val DESCRIPTION_KEY = "description_key"
        const val PERIOD_COUNT_KEY = "period_count_key"
        const val PERIOD_DAYS_KEY = "period_days_key"
        const val TYPE_KEY = "type_key"
        const val PRIORITY_KEY = "priority_key"
        const val COLOR_KEY = "color_key"
        const val POSITION_KEY = "position_key"
    }
}