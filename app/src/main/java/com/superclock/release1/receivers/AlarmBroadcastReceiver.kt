package com.superclock.release1.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import com.superclock.release1.services.AlarmService
import com.superclock.release1.services.RescheduleAlarmsService
import java.util.*


class AlarmBroadcastReceiver : BroadcastReceiver()
 {

  override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            val toastText = String.format("Alarm Reboot")
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            startRescheduleAlarmsService(context)
        } else {
            val toastText = String.format("Alarm Received")
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            if (!intent.getBooleanExtra(RECURRING, false)) {
                startAlarmService(context, intent)
            }
            run {
                if (alarmIsToday(intent)) {
                    startAlarmService(context, intent)
                }
            }
        }
    }

    private fun alarmIsToday(intent: Intent): Boolean {
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        val today: Int = calendar.get(Calendar.DAY_OF_WEEK)
        when (today) {
            Calendar.MONDAY -> {
                return intent.getBooleanExtra(MONDAY, false)
            }
            Calendar.TUESDAY -> {
                return intent.getBooleanExtra(TUESDAY, false)
            }
            Calendar.WEDNESDAY -> {
                return intent.getBooleanExtra(WEDNESDAY, false)
            }
            Calendar.THURSDAY -> {
                return intent.getBooleanExtra(THURSDAY, false)
            }
            Calendar.FRIDAY -> {
                return intent.getBooleanExtra(FRIDAY, false)
            }
            Calendar.SATURDAY -> {
                return intent.getBooleanExtra(SATURDAY, false)
            }
            Calendar.SUNDAY -> {
                return intent.getBooleanExtra(SUNDAY, false)
            }
        }
        return false
    }

    private fun startAlarmService(context: Context, intent: Intent) {
        val intentService = Intent(context, AlarmService::class.java)
        intentService.putExtra(TITLE, intent.getStringExtra(TITLE))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService)
        } else {
            context.startService(intentService)
        }
    }

    private fun startRescheduleAlarmsService(context: Context) {
        val intentService = Intent(context, RescheduleAlarmsService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService)
        } else {
            context.startService(intentService)
        }
    }

    companion object {
        const val MONDAY = "MONDAY"
        const val TUESDAY = "TUESDAY"
        const val WEDNESDAY = "WEDNESDAY"
        const val THURSDAY = "THURSDAY"
        const val FRIDAY = "FRIDAY"
        const val SATURDAY = "SATURDAY"
        const val SUNDAY = "SUNDAY"
        const val RECURRING = "RECURRING"
        const val EVERYDAY = "EVERYDAY"
        const val TITLE = "TITLE"
    }

}

