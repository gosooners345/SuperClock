package com.superclock.release1.ui.createalarms

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class CreateAlarmViewModel : AndroidViewModel()
{
    private var alarmRepository: AlarmRepository? = null

    fun CreateAlarmViewModel(application: Application) {
        super(application)
        alarmRepository = AlarmRepository(application)
    }

    fun insert(alarm: Alarm?) {
        alarmRepository.insert(alarm)
    }
}