package com.superclock.release1.ui.alarms


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superclock.release1.OnToggleAlarmListener
import com.superclock.release1.R
import com.superclock.release1.data.Alarm
import java.util.*


class AlarmRecyclerViewAdapter(listener: OnToggleAlarmListener) :
    RecyclerView.Adapter<AlarmViewHolder>() {
    private var alarms: List<Alarm>
    private val listener: OnToggleAlarmListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        return AlarmViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = alarms[position]
        holder.bind(alarm)
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    fun setAlarms(alarms: List<Alarm>) {
        this.alarms = alarms
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: AlarmViewHolder) {
        super.onViewRecycled(holder)
        holder.alarmStarted.setOnCheckedChangeListener(null)
    }

    init {
        alarms = ArrayList()
        this.listener = listener
    }
}