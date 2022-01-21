package com.app.near_labs.ui.main.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.app.near_labs.R
import kotlinx.android.synthetic.main.activity_verification.*


// Verification Screen - Designed by Dhara Hirpara 20 Jan 2021 3:00 PM EST

class VerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set full screen window
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= 21) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        setContentView(R.layout.activity_verification)

        setupUI()
    }

    private fun setupUI() {

        // set UI according to Email or Phone
        val key = intent.getIntExtra("key", 0)
        tv_code_sent.text = if(key==0) getString(R.string.sent_code_email) else getString(R.string.sent_code_phone)
        tv_resend.text = if(key==0) getString(R.string.send_to_email) else getString(R.string.send_to_phone)
        tv_code_number.text = if(key==0) "johndoe@gmail.com" else "+1 (373) 383 9933"

        // go to create new account
        button_continue.setOnClickListener {
            this.startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        // cancel button click
        ic_cancel.setOnClickListener {
            onBackPressed()
        }
    }
}