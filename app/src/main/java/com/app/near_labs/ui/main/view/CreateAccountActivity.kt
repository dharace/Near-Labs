package com.app.near_labs.ui.main.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.near_labs.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_create_account.ic_cancel
import kotlinx.android.synthetic.main.activity_gift_n_f_t.*


// Create Near Account Screen - Designed by Dhara Hirpara 20 Jan 2021 4:00 PM EST

class CreateAccountActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_create_account)

        setupUI()
    }

    private fun setupUI() {

        // change create Near Account button color
        ed_wallet_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length >= 3){
                    button_create_account.isClickable = true
                    button_create_account.setBackgroundResource(R.drawable.round_corner_black_button)
                }
                else {
                    button_create_account.isClickable = false
                    button_create_account.setBackgroundResource(R.drawable.round_corner_grey_button)
                }
            }
        })

        button_create_account.setOnClickListener {
            this.startActivity(Intent(this, GiftNFTActivity::class.java))
        }

        // change full name label color on focus
        ed_name.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                tv_full_name.setTextColor(resources.getColor(R.color.blue_button))
            } else {
                tv_full_name.setTextColor(resources.getColor(R.color.grey_808080))
            }
        }

        // change wallet id label and icon color on focus
        ed_wallet_id.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                tv_wallet_id.setTextColor(resources.getColor(R.color.blue_button))
                iv_info.setColorFilter(
                    ContextCompat.getColor(this, R.color.blue_button),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            } else {
                tv_wallet_id.setTextColor(resources.getColor(R.color.grey_808080))
                iv_info.setColorFilter(
                    ContextCompat.getColor(this, R.color.grey_808080),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }

        // set privacy text
        val text =
            "<font color=#565656>By creating a NEAR account, you agree to the NEAR Wallet</font>" +
                    "<font color=#2F80ED>Terms of Service</font>" +
                    "<font color=#565656>  and </font>" +
                    "<font color=#2F80ED>Privacy Policy</font>"
        tv_privacy.setText(Html.fromHtml(text))

        // cancel button click
        ic_cancel.setOnClickListener {
            onBackPressed()
        }

    }
}