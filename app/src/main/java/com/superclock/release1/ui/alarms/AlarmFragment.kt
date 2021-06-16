package com.superclock.release1.ui.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.superclock.release1.databinding.FragmentStopwatchBinding
import com.superclock.release1.ui.stopwatch.StopwatchViewModel

class AlarmFragment : Fragment() {
    private lateinit var alarmViewModel: AlarmViewModel
    private var _binding: FragmentStopwatchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alarmViewModel =
            ViewModelProvider(this).get(AlarmViewModel::class.java)

        _binding = FragmentStopwatchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.stopwatchTV
        alarmViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}