package com.app.near_labs.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.recyclerview.widget.RecyclerView
import com.app.near_labs.R
import com.app.near_labs.data.model.NFTUser
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import kotlinx.android.synthetic.main.activity_gift_n_f_t.view.*
import kotlinx.android.synthetic.main.item_gift_nft.view.*
import kotlinx.android.synthetic.main.item_gift_nft.view.iv_selection
import javax.inject.Inject


class GiftNFTAdapter @Inject constructor(
) : RecyclerView.Adapter<GiftNFTAdapter.DataViewHolder>() {

    private var users: ArrayList<NFTUser> = ArrayList()

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: NFTUser) {

            itemView.tv_name.text = user.name
            itemView.tvId.text = user.id

            // set image avatar
            if(user.avatar != null){
                Glide.with(itemView.ivAvtar.context)
                    .load(user.avatar)
                    .into(itemView.ivAvtar)
            }else{

                // get first two initials to set avtar
                val strArray: List<String> = user.name!!.split(" ")
                val builder = StringBuilder()
                if (strArray.isNotEmpty()) builder.append(strArray[0], 0, 1)
                if (strArray.size > 1) builder.append(strArray[1], 0, 1)

                val url = GlideUrl(
                    "https://via.placeholder.com/100x100?text=$builder", LazyHeaders.Builder()
                        .addHeader("User-Agent", WebSettings.getDefaultUserAgent(itemView.context))
                        .build()
                )

                Glide.with(itemView.ivAvtar.context)
                    .load(url)
                    .into(itemView.ivAvtar)
            }

            if(user.selection){
                itemView.iv_selection.setImageResource(R.drawable.ic_select)
            }else{
                itemView.iv_selection.setImageResource(R.drawable.ic_unselected)
            }

            itemView.container.setOnClickListener {
                if(user.selection){
                    itemView.iv_selection.setImageResource(R.drawable.ic_select)
                }else{
                    itemView.iv_selection.setImageResource(R.drawable.ic_unselected)
                }
                user.selection = !user.selection
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_gift_nft, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(users: List<NFTUser>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

}