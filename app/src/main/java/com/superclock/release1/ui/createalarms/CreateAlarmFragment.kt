package com.superclock.release1.ui.createalarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.superclock.release1.R
import com.superclock.release1.utils.TimePickerUtil.getTimePickerHour
import com.superclock.release1.utils.TimePickerUtil.getTimePickerMinute
import java.util.*


class CreateAlarmFragment : Fragment() {
    @BindView(R.id.timePicker)
    var timePicker: TimePicker? = null
@BindView(R.id.recurringDaysChipGroup)
var recurrDaysChipGroup: ChipGroup? = null
    @BindView(R.id.recurringChipGroup)
    var recurrChipGroup: ChipGroup?=null

    @BindView(R.id.alarmTitle)
    var title: EditText? = null

    @BindView(R.id.scheduleAlarm)
    var scheduleAlarm: Button? = null

    @BindView(R.id.recurringChip)
    var recurring: Chip? = null
    @BindView(R.id.everydayChip)
    var everyday: Chip? = null
    
    @BindView(R.id.mondayChip)
    var mon: Chip? = null

    @BindView(R.id.tuesdayChip)
    var tue: Chip? = null

    @BindView(R.id.wednesdayChip)
    var wed: Chip? = null

    @BindView(R.id.thursdayChip)
    var thu: Chip? = null

    @BindView(R.id.fridayChip)
    var fri: Chip? = null

    @BindView(R.id.saturdayChip)
    var sat: Chip? = null

    @BindView(R.id.sundayChip)
    var sun: Chip? = null

var everydayCheck : Boolean? = null
    var recurringCheck: Boolean? = null


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
     timePicker = view.findViewById<TimePicker>(R.id.timePicker)

        ButterKnife.bind(this,view)
        //Repeating alarms
        recurring!!.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                recurrDaysChipGroup?.visibility = View.VISIBLE
            everyday?.visibility = View.VISIBLE
            }
            else {
                recurrDaysChipGroup?.visibility= View.GONE
                everyday?.visibility = View.GONE
            }
        }
        scheduleAlarm!!.setOnClickListener { v ->
            scheduleAlarm()
            Navigation.findNavController(v)
                .navigate(R.id.action_createAlarmFragment_to_alarmsListFragment)
        }



        return view
    }
    private fun scheduleAlarm() {
        val alarmId: Int = Random().nextInt(Int.MAX_VALUE)
        val alarm = Alarm(
            alarmId,
            getTimePickerHour(timePicker!!),
            getTimePickerMinute(timePicker!!),
            title!!.text.toString(),
            true,
            recurring!!.isChecked,
            mon!!.isChecked,
            tue!!.isChecked,
            wed!!.isChecked,
            thu!!.isChecked,
            fri!!.isChecked,
            sat!!.isChecked,
            sun!!.isChecked
        )
        createAlarmViewModel.insert(alarm)
        alarm.schedule(context)
    }

    //OnCreate
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAlarmViewModel = ViewModelProviders.of(this).get(CreateAlarmViewModel::class.java)





    }


}

