package com.moose.ecoba.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.moose.ecoba.R
import com.moose.ecoba.databinding.ActivityLoginBinding
import com.moose.ecoba.presentation.home.HomeActivity
import com.moose.ecoba.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private val viewmodel: LoginViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // the button onClick listener
        with(binding){
            submitBtn.setOnClickListener {
                when {
                    email.text!!.isEmpty() -> emailLayout.error = "This field is required"
                    password.text!!.isEmpty() -> passwordLayout.error = "This field is required"
                    else -> viewmodel.login(email.text.toString(), password.text.toString())
                }
            }
        }

        // Observe the results from the login process
        viewmodel.result.observe(this, { result ->
            result.onError { toast(it)  }

            result.onSuccess {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        })

        // observe the loading status and show the relevant widget
        viewmodel.loading.observe(this, { loading ->
            with(binding){
                if (loading) {
                    submitBtn.hide()
                    loginProgress.show()
                } else {
                    loginProgress.hide()
                    submitBtn.show()
                }
            }
        })
    }
}