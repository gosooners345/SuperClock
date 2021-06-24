package com.superclock.release1.async;

import android.os.AsyncTask;

import com.superclock.release1.data.Alarm;
import com.superclock.release1.data.AlarmDao;

public class DeleteAsync extends AsyncTask<Alarm, Void, Void> {

    private final AlarmDao mAlarmDao;

    public DeleteAsync(AlarmDao dao) {
        mAlarmDao= dao;
    }

    @Override
    protected Void doInBackground(Alarm... alarm) {
        mAlarmDao.delete(alarm);
        return null;
    }
}