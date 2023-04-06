package com.example.kafkatest.model

data class ClientTargetAudiences (
        val id: String,
        val profileId: String,
        val targetAudiences: String,
        val source: String?
)