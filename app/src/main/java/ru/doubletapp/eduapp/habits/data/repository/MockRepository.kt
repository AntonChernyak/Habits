package ru.doubletapp.eduapp.habits.data.repository

import android.graphics.Color
import android.util.Log
import ru.doubletapp.eduapp.habits.data.model.Habit
import ru.doubletapp.eduapp.habits.data.model.HabitTypeEnum

object MockRepository {
    val list: MutableList<Habit> by lazy {
        createHabitsRepository()
    }

    fun addHabit(position: Int = list.size, habit: Habit) {
        list.add(position, habit)
    }

    private fun createHabitsRepository(): MutableList<Habit> {
        val initialList = mutableListOf<Habit>()
        Habit(
            title = "Погладить кота",
            priority = "4",
            periodCount = "3",
            periodDays = "1",
            color = Color.RED
        ).apply { initialList.add(this) }

        Habit(
            title = "Покормить кота",
            description = "Лучше кормить кота, а то он будет злиться. А до этого лучше не доводить, ибо страшен кот в гневе!",
            priority = "5",
            periodCount = "4",
            periodDays = "1",
            color = Color.MAGENTA
        ).apply { initialList.add(this) }

        Habit(
            title = "Погладить кота",
            priority = "4",
            periodCount = "3",
            periodDays = "1"
        ).apply { initialList.add(this) }

        Habit(
            title = "Покормить кота",
            description = "Лучше кормить кота, а то он будет злиться. А до этого лучше не доводить, ибо страшен кот в гневе!",
            priority = "5",
            periodCount = "4",
            periodDays = "1",
            color = Color.parseColor("#283593")
        ).apply { initialList.add(this) }

        Habit(
            title = "Погладить кота",
            priority = "4",
            periodCount = "3",
            periodDays = "1",
            color = Color.RED
        ).apply { initialList.add(this) }

        Habit(
            title = "Покормить кота",
            description = "Лучше кормить кота, а то он будет злиться. А до этого лучше не доводить, ибо страшен кот в гневе!",
            priority = "1",
            periodCount = "4",
            periodDays = "1",
            color = Color.MAGENTA
        ).apply { initialList.add(this) }

        Habit(
            title = "Погладить кота",
            priority = "4",
            periodCount = "3",
            periodDays = "1",
            color = Color.RED
        ).apply { initialList.add(this) }

        Habit(
            title = "Покормить кота Покормить кота Покормить кота Покормить кота Покормить кота",
            description = "Лучше кормить кота, а то он будет злиться. А до этого лучше не доводить, ибо страшен кот в гневе!",
            priority = "5",
            periodCount = "4",
            periodDays = "1",
            color = Color.MAGENTA
        ).apply { initialList.add(this) }

        Habit(
            title = "Погладить кота",
            priority = "4",
            periodCount = "3",
            periodDays = "1",
            color = Color.RED
        ).apply { initialList.add(this) }

        Habit(
            title = "Покормить кота",
            description = "Лучше кормить кота, а то он будет злиться. А до этого лучше не доводить," +
                    " ибо страшен кот в гневе! Лучше кормить кота, а то он будет злиться. А до этого " +
                    "лучше не доводить, ибо страшен кот в гневе! Ещё текст выаоы щыо аыоаш щыоао ыщаоыщ оаыщвоа " +
                    "ыоашщ ыоашщоы щаоыв оаышо аышваошв ыоащ оащыо ащывоа щоыща ывщ",
            priority = "5",
            periodCount = "4",
            periodDays = "1",
            color = Color.MAGENTA
        ).apply { initialList.add(this) }

        Habit(
            title = "Погладить кота",
            priority = "4",
            periodCount = "3",
            periodDays = "1",
            color = Color.RED
        ).apply { initialList.add(this) }

        Habit(
            title = "Покормить кота",
            description = "Лучше кормить кота, а то он будет злиться. А до этого лучше не доводить, ибо страшен кот в гневе!",
            priority = "5",
            periodCount = "4",
            periodDays = "1",
            type = HabitTypeEnum.BAD_HABIT,
            color = Color.MAGENTA
        ).apply { initialList.add(this) }

        return initialList
    }
}