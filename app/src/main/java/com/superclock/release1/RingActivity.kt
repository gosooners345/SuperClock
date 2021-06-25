package com.superclock.release1


import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.superclock.release1.data.Alarm
import com.superclock.release1.receivers.AlarmBroadcastReceiver.Companion.SNOOZE
import com.superclock.release1.services.AlarmService
import java.lang.Exception
import java.util.*


class RingActivity : AppCompatActivity() {

    var dismiss: Button? = null
    var snooze: Button? = null
    var snoozeLength : Int = 0
    var clock: ImageView? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ring)
        dismiss = findViewById(R.id.activity_ring_dismiss)
        clock = findViewById(R.id.activity_ring_clock)
        snooze = findViewById(R.id.activity_ring_snooze)
try {
    snoozeLength = intent!!.getIntExtra(SNOOZE,0)
}
catch(Ex: Exception){
    Toast.makeText(this,String.format(Ex.message+"\r\n"+Ex.printStackTrace().toString()),Toast.LENGTH_SHORT).show()
    Log.e("INTENTERR", Ex.printStackTrace().toString())

    snoozeLength = 10
}
        dismiss!!.setOnClickListener {
            val intentService = Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()
        }

        snooze!!.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.MINUTE, snoozeLength)
            val alarm = Alarm(
                Random().nextInt(Int.MAX_VALUE),
                calendar[Calendar.HOUR_OF_DAY],
                calendar[Calendar.MINUTE],
                "Snooze", snoozeLength,
                true,
                created = System.currentTimeMillis(),
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
            alarm.schedule(applicationContext)
            val intentService = Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()
        }



        animateClock()
    }

    private fun animateClock() {
        val rotateAnimation = ObjectAnimator.ofFloat(clock, "rotation", 0f, 20f, 0f, -20f, 0f)
        rotateAnimation.repeatCount = ValueAnimator.INFINITE
        rotateAnimation.duration = 800
        rotateAnimation.start()
    }
}
