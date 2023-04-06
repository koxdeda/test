package com.example.kafkatest.model


data class Client(
    val id: String,
    val client_id: String,
    val loyaltyProgram: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val email: String,
    val dateOfBirth: String,
    val sex: String,
    val status: String,
    val phone: String?
)
