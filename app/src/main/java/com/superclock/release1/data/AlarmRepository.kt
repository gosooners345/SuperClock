package com.superclock.release1.data

import android.app.Application
import androidx.lifecycle.LiveData


class AlarmRepository(application: Application?) {
    private lateinit var alarmDao: AlarmDao
    val alarmsLiveData: LiveData<List<Alarm?>?>?

    fun insert(alarm: Alarm?) {
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.insert(alarm) }
    }

    fun update(alarm: Alarm?) {
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.update(alarm) }
    }

    init {
        val db: AlarmDatabase? = application?.let { AlarmDatabase.getDatabase(it) }
        if (db != null) {
            alarmDao = db.alarmDao()!!
        }
        alarmsLiveData = alarmDao.alarms
    }
}