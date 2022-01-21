package com.app.near_labs.ui.main.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.app.near_labs.R
import kotlinx.android.synthetic.main.activity_login.*


// Login Screen - Designed by Dhara Hirpara 20 Jan 2021 2:00 PM EST

class LoginActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_login)

        setupUI()
    }

    // set up UI
    private fun setupUI() {
        onClickEmail()
        onClickPhone()
        onClickGetStart()

        // set privacy text
        val text = "<font color=#565656>by clicking continue you must agree to near labs\n</font>" +
                "<font color=#2F80ED>Terms & Conditions</font>" +
                "<font color=#565656>  and </font>" +
                "<font color=#2F80ED>Privacy Policy</font>"
        tv_privacy.setText(Html.fromHtml(text))
    }

    //on click get started
    private fun onClickGetStart() {
        button_get_start.setOnClickListener {
            val myIntent = Intent(this, VerificationActivity::class.java)
            myIntent.putExtra("key", if(ed_email.visibility == View.VISIBLE)  0 else 1) //Optional parameters
            this.startActivity(myIntent)
        }
    }

    // onclick email
    private fun onClickEmail() {
        tab_email.setOnClickListener {
            tab_email.setTextColor(resources.getColor(R.color.colorPrimary))
            tab_phone.setTextColor(resources.getColor(R.color.grey_hint))
            tab_email.background = resources.getDrawable(R.drawable.round_corner_grey)
            tab_phone.background = null
            ed_email.visibility = View.VISIBLE
            ed_phone.visibility = View.GONE
        }
    }

    // onclick phone
    private fun onClickPhone() {
        tab_phone.setOnClickListener {
            tab_email.setTextColor(resources.getColor(R.color.grey_hint))
            tab_phone.setTextColor(resources.getColor(R.color.colorPrimary))
            tab_email.background = null
            tab_phone.background = resources.getDrawable(R.drawable.round_corner_grey)
            ed_email.visibility = View.GONE
            ed_phone.visibility = View.VISIBLE
        }
    }
}