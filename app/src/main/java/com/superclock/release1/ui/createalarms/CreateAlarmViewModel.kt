package com.superclock.release1.ui.createalarms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.superclock.release1.App
import com.superclock.release1.data.Alarm
import com.superclock.release1.data.AlarmRepository


class CreateAlarmViewModel(application: Application) : AndroidViewModel(application)
{
    var alarmRepository: AlarmRepository?=AlarmRepository(application)



    fun insert(alarm: Alarm?) {
        alarmRepository?.insert(alarm)

    }
}