package com.superclock.release1

import com.superclock.release1.data.Alarm


interface OnToggleAlarmListener {
    fun onToggle(alarm: Alarm?)
}