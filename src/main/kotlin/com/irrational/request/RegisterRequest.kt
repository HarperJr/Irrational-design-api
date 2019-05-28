package com.irrational.request

data class RegisterRequest(
    val name: String,
    val password: String,
    val email: String
)