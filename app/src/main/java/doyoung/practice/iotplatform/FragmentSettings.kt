package doyoung.practice.iotplatform

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import doyoung.practice.iotplatform.databinding.ActivityMainBinding
import doyoung.practice.iotplatform.databinding.FragmentSettingsBinding
import java.util.*
import kotlin.concurrent.timer

class FragmentSettings : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    lateinit var MainActivity: MainActivity


    // AlertDialog의 설정 시간값을 위한 변수
    private var countdownSecond = 60
    private var currentCountdownDeciSecond = countdownSecond * 10

    // timer의 증가하는 수 확인을 위한 변수
    private var currentDeciSecond = 0

    private var timer: Timer? = null




    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) MainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            val bundle = bundleOf("valueKey" to "YES")
            setFragmentResult("request", bundle)
        }

        initCountdown()

        binding.layerHeart.setOnClickListener{
            timer = timer(initialDelay = 0, period = 100) {
                if (currentCountdownDeciSecond == 30) {
                    currentDeciSecond += 1

                    val minutes = currentDeciSecond.div(10) / 60
                    val seconds = currentDeciSecond.div(10) % 60
                    val deciSeconds = currentDeciSecond % 10

                    // 메인 스레드에서 작업
                    requireActivity().runOnUiThread{
                        binding.avInfo.setText("측정된 평균 심박수는 00입니다.")
                        binding.progressBar.isVisible = false
                    }
                }
                else {
                    currentCountdownDeciSecond -= 1
                    val seconds = currentCountdownDeciSecond / 10
                    val progress = (currentCountdownDeciSecond / (countdownSecond * 10f)) * 100

                    binding.root.post{
                        binding.progressBar.progress = progress.toInt()
                    }
                }

                if (currentDeciSecond == 0 && currentCountdownDeciSecond < 31 && currentCountdownDeciSecond % 10 == 0) {
                    val toneType = if(currentCountdownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
                    ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                        .startTone(toneType, 100)
                }
            }
        }
    }

    private fun initCountdown() {
        binding.progressBar.progress = 100
    }
}