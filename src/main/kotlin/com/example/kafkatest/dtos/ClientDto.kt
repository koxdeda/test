package com.example.kafkatest.dtos

data class ClientDto(
    val loyaltyProgram: String,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val email: String?,
    val dateOfBirth: String?,
    val sex: String?,
    val phone: String
)