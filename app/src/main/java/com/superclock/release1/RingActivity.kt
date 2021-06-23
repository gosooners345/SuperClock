package com.superclock.release1


import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.superclock.release1.data.Alarm
import com.superclock.release1.services.AlarmService
import java.util.*


class RingActivity : AppCompatActivity() {

    var dismiss: Button? = null
    var snooze: Button? = null
    var clock: ImageView? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ring)
        dismiss = findViewById(R.id.activity_ring_dismiss)
        clock = findViewById(R.id.activity_ring_clock)
        ButterKnife.bind(this)
        dismiss!!.setOnClickListener {
            fun onClick(v: View?) {
                val intentService = Intent(applicationContext, AlarmService::class.java)
                applicationContext.stopService(intentService)
                finish()
            }

        }
        snooze = findViewById(R.id.activity_ring_snooze)
        snooze!!.setOnClickListener {
            fun onClick(v: View?) {
                val calendar: Calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.add(Calendar.MINUTE, 10)
                val alarm = Alarm(
                    Random().nextInt(Int.MAX_VALUE),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    "Snooze",
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
