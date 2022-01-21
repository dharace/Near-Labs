package com.app.near_labs.ui.main.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.near_labs.R
import com.app.near_labs.data.model.User
import com.app.near_labs.ui.main.adapter.GiftNFTAdapter
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_home)

        setupUI()
    }

    private fun setupUI() {
        // get list
        val list: MutableList<User>  = intent.getSerializableExtra("list") as MutableList<User>

        // set list data
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GiftNFTAdapter()
        recyclerView.adapter = adapter

        adapter.apply {
            addData(list)
            notifyDataSetChanged()
        }
    }
}