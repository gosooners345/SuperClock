package com.superclock.release1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import android.widget.Toast


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(/*context: Context,importance Int,showBadge: Boolean,name:String,desc:String*/) {

          /*val channelID = "${context.packageName}-$name"
          val channel = NotificationChannel(channelID,name,importance)
          channel.description=desc
          channel.setShowBadge(showBadge)
          val notificationManager = context.getSystemService(NotificationManager::class.java)
          notificationManager.createNotificationChannel(channel)*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val serviceChannel = NotificationChannel(
                    CHANNEL_ID,
                    "Alarm Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                val manager = getSystemService(NotificationManager::class.java)
                manager.createNotificationChannel(serviceChannel)
            }




           /* val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)*/

    }

    companion object {
        const val CHANNEL_ID = "ALARM_SERVICE_CHANNEL"

    }
}
