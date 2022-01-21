package com.app.near_labs.ui.main.view

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.near_labs.R
import com.app.near_labs.data.model.NFTUser
import com.app.near_labs.ui.main.adapter.GiftNFTAdapter
import kotlinx.android.synthetic.main.activity_gift_n_f_t.*
import kotlinx.android.synthetic.main.dilog_allow_contacts.view.*
import javax.inject.Inject


// Gift NFT Screen - Designed by Dhara Hirpara 20 Jan 2021 4:00 PM EST

class GiftNFTActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: GiftNFTAdapter

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
        setContentView(R.layout.activity_gift_n_f_t)

        setupUI()
    }

    private fun setupUI() {

        // set list data
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GiftNFTAdapter()
        recyclerView.adapter = adapter
        adapter.apply {
            addData(getDummyUsers())
            notifyDataSetChanged()
        }

        // on click import contact
        tv_import.setOnClickListener {
            allowContactPermission()
        }

        // cancel button click
        ic_cancel.setOnClickListener {
            onBackPressed()
        }
    }

    private fun allowContactPermission() {

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dilog_allow_contacts, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)

        //show dialog
        val dialog: AlertDialog = dialogBuilder.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val wmlp = dialog.window!!.attributes
        wmlp.gravity = Gravity.BOTTOM
        dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        dialog.show()

        //set Listener
        dialogView.tv_allow.setOnClickListener(){
            dialog.dismiss()
            showImportGoogleContact()
        }

        dialogView.tv_deny.setOnClickListener(){
            dialog.dismiss()
        }

    }

    private fun showImportGoogleContact() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dilog_import_google_contacts, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)

        //show dialog
        val dialog: AlertDialog = dialogBuilder.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val wmlp = dialog.window!!.attributes
        wmlp.gravity = Gravity.BOTTOM
        dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        dialog.show()
    }

    // dummy list of users
    fun getDummyUsers(): List<NFTUser> {
        return listOf(
            NFTUser("Darlene Robertson", "@johndoe", null, false),
            NFTUser("Jacob Jones", "@johndoe", resources.getDrawable(R.drawable.avtar1), false),
            NFTUser("Jenny Wilson", "@johndoe", null, true),
            NFTUser("Ronald Richards", "@johndoe", null, true),
            NFTUser("Cameron Williamson", "@johndoe", null, false),
            NFTUser("Courtney Henry", "@johndoe", null, false),
            NFTUser("Wade Warren", "@johndoe", null, false),
            NFTUser("Darrell Steward", "@johndoe", resources.getDrawable(R.drawable.avtar1), true),
        )
    }
}