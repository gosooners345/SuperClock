package com.superclock.release1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.superclock.release1.data.Alarm
import java.lang.Exception


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        try{
        createNotificationChannel()}
        catch (Ex : Exception)
        {
            Log.e("NotifError",Ex.printStackTrace().toString())
            Toast.makeText(this,Ex.message,Toast.LENGTH_SHORT).show()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }

    companion object {
        const val CHANNEL_ID = "ALARM_SERVICE_CHANNEL"

    }
}
