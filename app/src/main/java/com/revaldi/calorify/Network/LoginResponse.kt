package com.revaldi.calorify.Network

data class LoginResponse(
    val code: Int,
    val message: String,
    val status: String,
    val user_id : String
)