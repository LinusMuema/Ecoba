package com.moose.ecoba.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Credentials(val email: String, val password: String)

@Serializable
data class UserResponse(
    val user: User,
    val token: String,
    val message: String,
    val success: Boolean,
)

@Entity
@Serializable
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val token: String,
    val email: String,
    val avatar: String,
    val msisdn: String,
    val status: Boolean,
    val verified: Boolean,
    val other_names: String,
    val account_verified: Boolean
)