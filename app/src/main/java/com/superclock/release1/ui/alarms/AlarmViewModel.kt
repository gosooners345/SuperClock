package com.superclock.release1.ui.alarms

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.superclock.release1.data.Alarm
import com.superclock.release1.data.AlarmRepository


class AlarmViewModel : ViewModel() {
    private var alarmRepository: AlarmRepository? = null
    private var alarmsLiveData: LiveData<List<Alarm?>?>? = null

    fun AlarmsListViewModel(application: Application) {

        alarmRepository = AlarmRepository(application)
        alarmsLiveData = alarmRepository!!.alarmsLiveData
    }

    fun update(alarm: Alarm?) {
        alarmRepository!!.update(alarm)
    }

    fun getAlarmsLiveData(): LiveData<List<Alarm?>?>? {
        return alarmsLiveData
    }
}