package com.superclock.release1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface AlarmDao {
    @Insert
    fun insert(alarm: Alarm?)

    @Query("DELETE FROM alarm_table")
    fun deleteAll()

    @get:Query("SELECT * FROM alarm_table ORDER BY alarmId ASC")
    val alarms: LiveData<List<Alarm?>?>?

    @Update
    fun update(alarm: Alarm?)


}
