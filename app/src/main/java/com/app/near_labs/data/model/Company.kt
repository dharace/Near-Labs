

package com.app.near_labs.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company  (
	@SerializedName("name") val name : String,
	@SerializedName("catchPhrase") val catchPhrase : String,
	@SerializedName("bs") val bs : String
): Serializable