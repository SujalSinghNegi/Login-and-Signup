package com.example.loginandsignup

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.loginandsignup.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.getValue

class SplashScreen : AppCompatActivity() {
    val binding by lazy{
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        lifecycleScope.launch {
            delay(1500)

            if (auth.currentUser != null) {
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                finish()
            }else {

                startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                finish()
            }

        }



    }

}