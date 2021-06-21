package com.superclock.release1.services

import android.content.Intent
import android.os.IBinder
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import com.superclock.release1.data.Alarm
import com.superclock.release1.data.AlarmRepository


class RescheduleAlarmsService : LifecycleService() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val alarmRepository = AlarmRepository(application)
        alarmRepository.alarmsLiveData!!.observe(this, object : Observer<List<Alarm?>?> {
            override fun onChanged(alarms: List<Alarm?>?) {
                for (a in alarms!!) {
                    if (a!!.started) {
                        a!!.schedule(applicationContext)
                    }
                }
            }

            })
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}
