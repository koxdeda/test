package com.example.kafkatest.model

data class ClientProduct (
        val id: String,
        val profileId: String,
        val productType: String,
        val lastDigits: String,
        val cardAccount: String
)