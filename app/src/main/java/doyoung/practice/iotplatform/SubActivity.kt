package doyoung.practice.iotplatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import doyoung.practice.iotplatform.databinding.ActivityMainBinding
import doyoung.practice.iotplatform.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val dayOfMonth = intent.getIntExtra("dayOfMonth", 0)

        binding.textViewInstruction.setText("${year}년 ${month+1}월 ${dayOfMonth}일의 산책 일지")

        binding.backToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("dayOfMonth", dayOfMonth)
            startActivity(intent)
        }
    }
}