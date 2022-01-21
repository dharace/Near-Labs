package com.app.near_labs.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geo (
	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
): Serializable