package com.superclock.release1.ui.alarms

//import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.superclock.release1.OnToggleAlarmListener
import com.superclock.release1.R
import com.superclock.release1.data.Alarm


class AlarmFragment : Fragment(),
    OnToggleAlarmListener {
    private var alarmRecyclerViewAdapter: AlarmRecyclerViewAdapter? = null
    private var alarmsListViewModel: AlarmViewModel? = null
    private var alarmsRecyclerView: RecyclerView? = null
    private var addAlarm: Button? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmRecyclerViewAdapter = AlarmRecyclerViewAdapter(this)
        alarmsListViewModel = of(this).get(AlarmViewModel::class.java)
    alarmsListViewModel?.getAlarmsLiveData()?.observe(this,
        { alarms ->
            if (alarms != null) {
                alarmRecyclerViewAdapter?.setAlarms(alarms as List<Alarm>)
            }
        })
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_alarms, container, false)
        alarmsRecyclerView = view.findViewById(R.id.fragment_listalarms_recylerView)
        alarmsRecyclerView!!.layoutManager = LinearLayoutManager(context)
        alarmsRecyclerView!!.adapter = alarmRecyclerViewAdapter
        addAlarm = view.findViewById(R.id.fragment_listalarms_addAlarm)
        addAlarm?.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_alarmsListFragment_to_createAlarmFragment)
        }
        return view
    }

    override fun onToggle(alarm: Alarm?) {
        if (alarm!!.started) {
            alarm!!.cancelAlarm(requireContext())
            alarmsListViewModel!!.update(alarm)
        } else {
            alarm!!.schedule(requireContext())
            alarmsListViewModel!!.update(alarm)
        }
    }
}