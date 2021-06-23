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
        if (alarm.title.length != 0) {
            alarmTitle.text =
                String.format("%s | %d | %d", alarm.title, alarm.alarmId, alarm.created)
        } else {
            alarmTitle.text = String.format("%s | %d | %d", "Alarm", alarm.alarmId, alarm.created)
        }
        alarmStarted.setOnCheckedChangeListener { buttonView, isChecked -> listener.onToggle(alarm) }
    }

    init {
        alarmTime = itemView.findViewById(R.id.item_alarm_time)
        alarmStarted = itemView.findViewById(R.id.item_alarm_started)
        alarmRecurring = itemView.findViewById(R.id.item_alarm_recurring)
        alarmRecurringDays = itemView.findViewById(R.id.item_alarm_recurringDays)
        alarmTitle = itemView.findViewById(R.id.item_alarm_title)
        this.listener = listener
    }
}