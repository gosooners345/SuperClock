package com.superclock.release1.async;

import android.os.AsyncTask;
import com.superclock.release1.data.Alarm;
import com.superclock.release1.data.AlarmDao;



public class UpdateAsync extends AsyncTask<Alarm,Void,Void>
{
    private final AlarmDao mAlarmDao;
    public UpdateAsync(AlarmDao alarmDao) {mAlarmDao = alarmDao;}

    @Override
    protected Void doInBackground(Alarm... alarms) {
        mAlarmDao.update(alarms);
        return null;
    }
}
