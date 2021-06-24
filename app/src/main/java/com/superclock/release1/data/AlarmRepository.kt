package com.superclock.release1.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.superclock.release1.async.DeleteAsync
import com.superclock.release1.async.InsertAsync
import com.superclock.release1.async.UpdateAsync


class AlarmRepository(application: Application?) {
    //private val alarmDao: AlarmDao
    val db : AlarmDatabase = AlarmDatabase.getDatabase(application)


    fun insert(alarm: Alarm?) {
       // AlarmDatabase.databaseWriteExecutor.execute { alarmDao.insert(alarm) }
        //db.alarmDao().insert(alarm)
        InsertAsync(db.alarmDao()).execute(alarm)
        Log.i("ALARM","Alarm Insertion Successful")
    }

    fun update(alarm: Alarm?) {
    //    AlarmDatabase.databaseWriteExecutor.execute { alarmDao.update(alarm) }
     UpdateAsync(db.alarmDao()).execute(alarm)
        Log.i("ALARM","Alarm Update Successful")
    }

    fun delete(alarm: Alarm?)
    {
        DeleteAsync(db.alarmDao()).execute(alarm)

//        db.alarmDao().delete(alarm)
        Log.i("ALARM","Alarm Deletion Successful")
     //   AlarmDatabase.databaseWriteExecutor.execute{alarmDao.delete(alarm)}

    }


    val alarmsLiveData: LiveData<List<Alarm?>?>
        get() = db.alarmDao().alarms

    /*private LiveData<List<Alarm>> alarmsLiveData;*/
    init {
        // alarmDao = db.alarmDao()

        //  alarmsLiveData = alarmDao.getAlarms();
    }
}