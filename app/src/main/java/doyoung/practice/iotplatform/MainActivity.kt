package doyoung.practice.iotplatform

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import doyoung.practice.iotplatform.databinding.ActivityMainBinding
import doyoung.practice.iotplatform.databinding.FragmentSettingsBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 페이지 데이터 로드하기 (A,B,C,D 프래그먼트를 목록에 담기)
        val list = listOf(FragmentSettings(), FragmentCalendar())
        // 2. 어댑터 생성하기
        val adapter = FragmentPagerAdapter(list, this)
        // 3. 어댑터와 뷰 페이저 연결하기
        binding.viewPager.adapter = adapter
        // 4. 탭 메뉴의 개수만큼, 탭 아이템의 제목을 목록으로 생성해둔다.
        val tabIcons = listOf(R.drawable.baseline_settings_24, R.drawable.baseline_calendar_today_24)
        // 5. 탭 레이아웃과 뷰 페이저 연결하기
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setIcon(tabIcons[position])
        }.attach()
    }
}

class FragmentPagerAdapter(
    val fragmentList: List<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList.get(position)
}