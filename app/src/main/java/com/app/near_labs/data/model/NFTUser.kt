package com.app.near_labs.data.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class NFTUser(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("avatar")
    val avatar: Drawable? = null,

    @field:SerializedName("selection")
    var selection: Boolean = false
)