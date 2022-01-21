package com.app.near_labs.data.repository

import com.app.near_labs.data.api.ApiService
import com.app.near_labs.data.model.User
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}