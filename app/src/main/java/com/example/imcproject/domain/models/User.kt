package com.example.imcproject.domain.models

data class User(
    val id: String,
    val email: String,
    val height: Double,
    val weight: Double,
    val gender: String,
    val age: Int
)
