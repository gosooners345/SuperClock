package com.superclock.release1.services


import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.Vibrator
import android.util.Log
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.superclock.release1.App.Companion.CHANNEL_ID
import com.superclock.release1.R
import com.superclock.release1.RingActivity
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.TITLE


class AlarmService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm)
        mediaPlayer!!.isLooping = true
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val notificationIntent = Intent(this, RingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val alarmTitle = String.format("%s Alarm",intent.getStringExtra(TITLE))
        val notification: Notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle(alarmTitle)
            .setContentText("Ring Ring .. Ring Ring")
            .setSmallIcon(R.drawable.ic_alarm_black_24dp)
            .setContentIntent(pendingIntent)
            .build()
        mediaPlayer!!.start()
        val pattern = longArrayOf(0, 100, 1000)
        vibrator!!.vibrate(pattern, 0)
        startForeground(1, notification)
        return START_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        vibrator!!.cancel()
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}