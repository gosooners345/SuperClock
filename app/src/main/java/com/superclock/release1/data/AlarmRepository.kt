package com.superclock.release1.data

import android.app.Application
import androidx.lifecycle.LiveData

class AlarmRepository(application: Application?) {
    private val alarmDao: AlarmDao
    fun insert(alarm: Alarm?) {
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.insert(alarm) }
    }

    fun update(alarm: Alarm?) {
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.update(alarm) }
    }

    fun delete(alarm: Alarm?)
    {
        AlarmDatabase.databaseWriteExecutor.execute{alarmDao.delete(alarm)}
    }

    val alarmsLiveData: LiveData<List<Alarm?>?>
        get() = alarmDao.alarms

    /*private LiveData<List<Alarm>> alarmsLiveData;*/
    init {
        val db = AlarmDatabase.getDatabase(application)
        alarmDao = db.alarmDao()

        //  alarmsLiveData = alarmDao.getAlarms();
    }
}