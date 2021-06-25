package com.superclock.release1.ui.alarms

//import android.R
import android.app.Application
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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.superclock.release1.App
import com.superclock.release1.MainActivity
import com.superclock.release1.OnToggleAlarmListener
import com.superclock.release1.R
import com.superclock.release1.data.Alarm
import com.superclock.release1.data.AlarmRepository


class AlarmFragment : Fragment(),
    OnToggleAlarmListener {
    private lateinit var alarmRecyclerViewAdapter: AlarmRecyclerViewAdapter
    private lateinit var alarmsListViewModel: AlarmViewModel
    private lateinit var alarmsRecyclerView: RecyclerView
    private var addAlarm: Button? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmRecyclerViewAdapter = AlarmRecyclerViewAdapter(this)
        alarmsListViewModel = of(this).get(AlarmViewModel::class.java)
        alarmsListViewModel.getAlarmsLiveData()?.observe(this, { alarms ->
            if (alarms != null)
                alarmRecyclerViewAdapter.setAlarms(alarms as List<Alarm>)
        })

    }
    //Handles creating the scene
    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_alarms, container, false)
        alarmsRecyclerView = view.findViewById(R.id.fragment_listalarms_recylerView)
        alarmsRecyclerView.layoutManager = LinearLayoutManager(context)
        alarmsRecyclerView.adapter = alarmRecyclerViewAdapter
        addAlarm = view.findViewById(R.id.fragment_listalarms_addAlarm)
        addAlarm?.setOnClickListener { v ->
            Navigation.findNavController(v)
                .navigate(R.id.action_alarmsListFragment_to_createAlarmFragment)

        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(alarmsRecyclerView)

        return view
    }

    //Enables swipe to delete
    var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            //Swipe to delete handler
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteAlarm(viewHolder.adapterPosition)
            }
        }

    //Delete Alarms
    fun deleteAlarm(position: Int) {
        var alarmList = alarmRecyclerViewAdapter.getAlarms()
        var alarm = alarmList[position]

        //   alarmRecyclerViewAdapter.deleteAlarm(alarm)
        alarmsListViewModel.alarmRepository?.delete(alarm)
        alarmRecyclerViewAdapter.notifyDataSetChanged()

    }
    // Toggles the alarm on or off
    override fun onToggle(alarm: Alarm?) {
        if (alarm!!.started) {
            alarm.cancelAlarm(requireContext())
            alarmsListViewModel.update(alarm)

        } else {
            alarm.schedule(requireContext())
            alarmsListViewModel.update(alarm)
        }
    }


}