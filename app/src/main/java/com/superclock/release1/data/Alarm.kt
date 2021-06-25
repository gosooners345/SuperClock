package com.superclock.release1.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.superclock.release1.receivers.AlarmBroadcastReceiver
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.FRIDAY
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.MONDAY
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.RECURRING
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.SATURDAY
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.SNOOZE
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.SUNDAY
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.THURSDAY
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.TITLE
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.TUESDAY
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.WEDNESDAY
import com.superclock.release1.ui.createalarms.DayUtil
import java.util.*


@Entity(tableName = "alarm_table")
class Alarm(
    @field:PrimaryKey val alarmId: Int,
    val hour: Int,
    val minute: Int,
    val title: String,
    val snooze: Int,
    var started: Boolean,
     var created:Long,
    val recurring: Boolean,
    val monday: Boolean,
    val tuesday: Boolean,
    val wednesday: Boolean,
    val thursday: Boolean,
    val friday: Boolean,
    val saturday: Boolean,
    val sunday: Boolean
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
        intent.putExtra(SNOOZE,snooze)
        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId, intent, 0
        )
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // if alarm time has already passed, increment day by 1
        if (calendar.timeInMillis <= System.currentTimeMillis()) {
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
                calendar.timeInMillis,
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
                calendar.timeInMillis,
                RUN_DAILY,
                alarmPendingIntent
            )
        }
        started = true
    }

//    public var Created:Long = created
    fun getRecurringDaysText(): String? {
        if (!recurring) {
            return null
        }
        var days = ""
        if (monday) {
            days += "Mon "
        }
        if (tuesday) {
            days += "Tues "
        }
        if (wednesday) {
            days += "Wed "
        }
        if (thursday) {
            days += "Thurs "
        }
        if (friday) {
            days += "Fri "
        }
        if (saturday) {
            days += "Sat "
        }
        if (sunday) {
            days += "Sun "
        }
        return days
    }





    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0)
        alarmManager.cancel(alarmPendingIntent)
        started = false
        val toastText =
            String.format("Alarm cancelled for %02d:%02d with id %d", hour, minute, alarmId)
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
        Log.i("cancel", toastText)
    }

}