package com.superclock.release1.ui.timer

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

class TimerFragment : Fragment() {
    private lateinit var timerViewModel: TimerViewModel
    private var _binding: FragmentStopwatchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        timerViewModel=
            ViewModelProvider(this).get(TimerViewModel::class.java)

        _binding = FragmentStopwatchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.stopwatchTV
        timerViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}