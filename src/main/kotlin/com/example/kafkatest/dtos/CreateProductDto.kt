package com.example.kafkatest.dtos

import java.util.UUID

data class CreateProductDto(
    val profileId: UUID,
    val product: String
)
