package com.app.near_labs.data.api

import com.app.near_labs.data.model.User
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}