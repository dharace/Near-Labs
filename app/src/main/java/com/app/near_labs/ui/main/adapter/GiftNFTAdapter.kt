package com.app.near_labs.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.app.near_labs.R
import com.app.near_labs.data.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import kotlinx.android.synthetic.main.item_gift_nft.view.*
import javax.inject.Inject


class GiftNFTAdapter @Inject constructor(
) : RecyclerView.Adapter<GiftNFTAdapter.DataViewHolder>(), Filterable {

    private var users: ArrayList<User> = ArrayList()
    var usersFiltered: ArrayList<User> = ArrayList()

    var onItemClick: ((User) -> Unit)? = null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(users[adapterPosition])
                if (users[adapterPosition].selection) {
                    itemView.iv_selection.setImageResource(R.drawable.ic_select)
                } else {
                    itemView.iv_selection.setImageResource(R.drawable.ic_unselected)
                }
                users[adapterPosition].selection = !users[adapterPosition].selection
            }
        }

        fun bind(user: User) {

            itemView.tv_name.text = user.name
            itemView.tvId.text = "@" + user.username

            // set image avatar
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

            itemView.iv_selection.setImageResource(if (user.selection) R.drawable.ic_select else R.drawable.ic_unselected)

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


    fun addData(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) usersFiltered = users else {
                    val filteredList = ArrayList<User>()
                    users
                        .filter {
                            (it.name.contains(constraint!!)) or
                                    (it.username.contains(constraint)) or
                                    (it.email.contains(constraint))
                        }
                        .forEach { filteredList.add(it) }
                    usersFiltered = filteredList

                }
                return FilterResults().apply { values = usersFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                usersFiltered = if (results?.values == null) ArrayList()
                else results.values as ArrayList<User>
                addData(usersFiltered)
                notifyDataSetChanged()
            }
        }
    }


}

