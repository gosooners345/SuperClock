package com.superclock.release1.ui.alarms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.superclock.release1.data.Alarm
import com.superclock.release1.data.AlarmRepository


class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private var alarmRepository: AlarmRepository? = AlarmRepository(application)
    private var alarmsLiveData: LiveData<List<Alarm?>?>? = alarmRepository!!.alarmsLiveData

   /* fun AlarmViewModel(application: Application) {

        alarmRepository = AlarmRepository(application)
        alarmsLiveData = alarmRepository!!.alarmsLiveData

    }*/

    fun update(alarm: Alarm?) {
        alarmRepository!!.update(alarm)
    }

    fun getAlarmsLiveData(): LiveData<List<Alarm?>?>? {
        return alarmsLiveData
    }
}