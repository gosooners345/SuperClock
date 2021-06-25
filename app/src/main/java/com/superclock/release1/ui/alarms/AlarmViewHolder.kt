package com.superclock.release1.ui.alarms

import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.superclock.release1.OnToggleAlarmListener
import com.superclock.release1.R
import com.superclock.release1.data.Alarm

class AlarmViewHolder(itemView: View, listener: OnToggleAlarmListener) :
    RecyclerView.ViewHolder(itemView) {
    private val alarmTime: TextView
    private val alarmRecurring: ImageView
    private val alarmRecurringDays: TextView
    private val alarmSnooze : TextView
    private val alarmTitle: TextView
     val alarmStarted: Switch
    private val listener: OnToggleAlarmListener
    fun bind(alarm: Alarm) {
        val alarmText = String.format("%02d:%02d", alarm.hour, alarm.minute)
        alarmTime.text = alarmText
        alarmStarted.isChecked = alarm.started
        if (alarm.recurring) {
            alarmRecurring.setImageResource(R.drawable.ic_alarm_black_24dp)
            alarmRecurringDays.text = alarm.getRecurringDaysText()
        } else {
            alarmRecurring.setImageResource(R.drawable.ic_alarm_black_24dp)
            alarmRecurringDays.text = "Once Off"
        }
if(alarm.snooze!=0)
    alarmSnooze.text = String.format("%02d minutes",alarm.snooze)
        else
            alarmSnooze

        if (alarm.title.length != 0) {
            alarmTitle.text =
                String.format("%s", alarm.title)
        } else {
            alarmTitle.text = String.format("%s", "Alarm", )
        }
        alarmStarted.setOnCheckedChangeListener { buttonView, isChecked -> listener.onToggle(alarm) }
    }

    init {
        alarmTime = itemView.findViewById(R.id.alarmTime)
        alarmStarted = itemView.findViewById(R.id.alarmToggleSwitch)
        alarmSnooze = itemView.findViewById(R.id.alarmSnoozeLength)
        alarmRecurring = itemView.findViewById(R.id.recurringCheck)
        alarmRecurringDays = itemView.findViewById(R.id.item_alarm_recurringDays)
        alarmTitle = itemView.findViewById(R.id.alarmtitleTV)
        this.listener = listener
    }
}