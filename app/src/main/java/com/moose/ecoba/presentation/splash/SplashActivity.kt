package com.moose.ecoba.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moose.ecoba.R
import com.moose.ecoba.data.Preferences
import com.moose.ecoba.databinding.ActivitySplashBinding
import com.moose.ecoba.presentation.home.HomeActivity
import com.moose.ecoba.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferences: Preferences
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigate = timerTask {
            // get the token
            val token = preferences.getToken()
            val context = this@SplashActivity

            // navigate to required screen
            if (token == null) startActivity(Intent(context, LoginActivity::class.java))
            else startActivity(Intent(context, HomeActivity::class.java))
            finish()
        }
        Timer().schedule(navigate, 3000)
    }
}