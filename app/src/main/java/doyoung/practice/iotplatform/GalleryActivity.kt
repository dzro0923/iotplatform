package doyoung.practice.iotplatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import doyoung.practice.iotplatform.databinding.ActivityGalleryBinding
import doyoung.practice.iotplatform.databinding.ActivitySubBinding

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webView = binding.webView
        val progressBar = binding.progressBar

        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val dayOfMonth = intent.getIntExtra("dayOfMonth", 0)

        binding.backToSub.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("dayOfMonth", dayOfMonth)
            startActivity(intent)
            finish()
        }

        // 웹뷰 띄우기 _ 여기에 사진들이 들어간 우리만의 페이지가 뜰 것임
        webView.apply {
            webViewClient = GalleryWebViewClient(progressBar)
            settings.javaScriptEnabled = true
        }
        webView.loadUrl("https://www.naver.com")
    }
}