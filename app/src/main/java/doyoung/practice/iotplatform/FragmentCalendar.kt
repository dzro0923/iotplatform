package doyoung.practice.iotplatform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import doyoung.practice.iotplatform.databinding.FragmentCalendarBinding
import java.util.Calendar

class FragmentCalendar : Fragment() {

    lateinit var binding: FragmentCalendarBinding
    lateinit var MainActivity: MainActivity
    var count: Int = 0

    private lateinit var calendarView: CalendarView

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) MainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("request") { key, bundle ->
            // FragmentSettings의 btnStart가 눌러졌다면, bundle의 값이 YES로 참조됨.
            // 참조되는 값이 YES라면, 뷰 업데이트
            bundle.getString("valueKey")?.let { it ->
                if(it == "YES") {
                    count++
                    val countmsg = "이번 달 산책 횟수는 ${count}회 입니다."
                    binding.countText.setText(countmsg)
                }
            }
        }


        calendarView = binding.calendarView

        val today = Calendar.getInstance()

        calendarView.setDate(today.timeInMillis, true, true)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val intent = Intent(requireContext(), SubActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("dayOfMonth", dayOfMonth)
            startActivity(intent)
        }
    }
}


