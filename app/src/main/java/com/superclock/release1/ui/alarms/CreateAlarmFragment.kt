package com.superclock.release1.ui.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.superclock.release1.R
import com.superclock.release1.databinding.FragmentAlarmCreateBinding

class CreateAlarmFragment : Fragment() {

    private lateinit var createAlarmViewModel: CreateAlarmViewModel
  //Not implementing yet
    //  private var _binding: FragmentAlarmCreateBinding?=null
//private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alarm_create,container,false)
val timePIcker = view.findViewById<TimePicker>(R.id.timePicker)


return view
    }



}