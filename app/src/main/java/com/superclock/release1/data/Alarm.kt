package com.superclock.release1.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.superclock.release1.receivers.AlarmBroadcastReceiver
import java.util.*


@Entity(tableName = "alarm_table")
class Alarm(
    @field:PrimaryKey private val alarmId: Int,
    private val hour: Int,
    private val minute: Int,
    private val title: String,
    private var started: Boolean,
    private val recurring: Boolean,
    private val monday: Boolean,
    private val tuesday: Boolean,
    private val wednesday: Boolean,
    private val thursday: Boolean,
    private val friday: Boolean,
    private val saturday: Boolean,
    private val sunday: Boolean
) {
    fun schedule(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra(RECURRING, recurring)
        intent.putExtra(MONDAY, monday)
        intent.putExtra(TUESDAY, tuesday)
        intent.putExtra(WEDNESDAY, wednesday)
        intent.putExtra(THURSDAY, thursday)
        intent.putExtra(FRIDAY, friday)
        intent.putExtra(SATURDAY, saturday)
        intent.putExtra(SUNDAY, sunday)
        intent.putExtra(TITLE, title)
        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId, intent, 0
        )
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // if alarm time has already passed, increment day by 1
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        }
        if (!recurring) {
            var toastText: String? = null
            try {
                toastText = java.lang.String.format(
                    "One Time Alarm %s scheduled for %s at %02d:%02d",
                    title, DayUtil.toDay(calendar.get(Calendar.DAY_OF_WEEK)),
                    hour,
                    minute,
                    alarmId
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                alarmPendingIntent
            )
        } else {
            val toastText = java.lang.String.format(
                "Recurring Alarm %s scheduled for %s at %02d:%02d",
                title, getRecurringDaysText(),
                hour,
                minute,
                alarmId
            )
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
            val RUN_DAILY = (24 * 60 * 60 * 1000).toLong()
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                RUN_DAILY,
                alarmPendingIntent
            )
        }
        started = true
    }
}