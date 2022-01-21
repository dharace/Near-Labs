package com.app.near_labs.ui.main.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.near_labs.R
import com.app.near_labs.data.model.NFTUser
import com.app.near_labs.data.model.User
import com.app.near_labs.ui.main.adapter.GiftNFTAdapter
import com.app.near_labs.ui.main.viewmodel.UserViewModel
import com.app.near_labs.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_gift_n_f_t.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dilog_allow_contacts.view.*
import java.io.Serializable
import javax.inject.Inject


// Gift NFT Screen - Designed by Dhara Hirpara 20 Jan 2021 4:00 PM EST

@AndroidEntryPoint
class GiftNFTActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var adapter: GiftNFTAdapter

    private var users: ArrayList<User> = ArrayList()
    private var isSearch: Boolean = false

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
        setupAPICall()
    }

    private fun setupUI() {

        // set list data
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GiftNFTAdapter()
        recyclerView.adapter = adapter

        button_send_gift.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            myIntent.putExtra("list", if(isSearch) adapter.usersFiltered else users)
            this.startActivity(myIntent)
        }

        // on click import contact
        tv_import.setOnClickListener {
            allowContactPermission()
        }

        // cancel button click
        ic_cancel.setOnClickListener {
            onBackPressed()
        }

        // change create Near Account button color
        ed_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 0) {
                    adapter.filter.filter(s)
                    isSearch = true
                } else {
                    renderList(users)
                    isSearch = false
                }
            }
        })
    }

    private fun setupAPICall() {
        viewModel.fetchUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { usersData: List<User> ->
                        renderList(usersData)
                        users = usersData as ArrayList<User>
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.apply {
            addData(users)
            notifyDataSetChanged()
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
        val dialogView = LayoutInflater.from(this).inflate(
            R.layout.dilog_import_google_contacts,
            null
        )
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