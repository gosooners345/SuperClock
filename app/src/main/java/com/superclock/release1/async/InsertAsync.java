package com.superclock.release1.async;

import android.os.AsyncTask;
import com.superclock.release1.data.Alarm;
import com.superclock.release1.data.AlarmDao;


public class InsertAsync extends AsyncTask<Alarm,Void,Void>{
    private final AlarmDao mAlarmDao;
    public InsertAsync(AlarmDao alarmDao)
    {
        mAlarmDao = alarmDao;

    }

    @Override
    protected Void doInBackground(Alarm... alarms) {
            mAlarmDao.insert(alarms);
            return null;
    }
}
