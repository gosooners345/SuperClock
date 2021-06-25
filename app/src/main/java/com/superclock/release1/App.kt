package com.superclock.release1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.widget.Toast


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(/*context: Context,importance Int,showBadge: Boolean,name:String,desc:String*/) {
if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
{
    Toast.makeText(this,"Testing Notifications",Toast.LENGTH_SHORT).show()
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
