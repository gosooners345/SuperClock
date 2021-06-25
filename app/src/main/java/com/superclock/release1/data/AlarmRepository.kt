package com.superclock.release1.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.superclock.release1.async.DeleteAsync
import com.superclock.release1.async.InsertAsync
import com.superclock.release1.async.UpdateAsync

/*
Summary: This class is used to handle the CRUD Operations of the alarm database class
Author: Brandon Guerin
Languages Used: Kotlin
 */

class AlarmRepository(application: Application?) {
    //DB Variable needed for this class
    private val db : AlarmDatabase = AlarmDatabase.getDatabase(application)

    //Add new alarm
    fun insert(alarm: Alarm?) {

        InsertAsync(db.alarmDao()).execute(alarm)
        Log.i("ALARM","Alarm Insertion Successful")
    }
    //Update Alarm
    fun update(alarm: Alarm?) {

     UpdateAsync(db.alarmDao()).execute(alarm)
        Log.i("ALARM","Alarm Update Successful")
    }
    //Delete Alarm
    fun delete(alarm: Alarm?)
    {
        DeleteAsync(db.alarmDao()).execute(alarm)
        Log.i("ALARM","Alarm Deletion Successful")
    }

    //Retrieve alarm list
    val alarmsLiveData: LiveData<List<Alarm?>?>
        get() = db.alarmDao().alarms
}