package com.moose.ecoba.data.remote

import com.moose.ecoba.domain.models.Credentials
import com.moose.ecoba.domain.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiEndpoints {

    @POST("/api/login")
    suspend fun login(@Body credentials: Credentials): UserResponse
}