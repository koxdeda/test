package com.example.kafkatest.repository

import com.example.kafkatest.dtos.ClientDto
import com.example.kafkatest.dtos.CreateProductDto
import com.example.kafkatest.dtos.CreateProductResponseDto
import com.example.kafkatest.model.Client
import com.example.kafkatest.model.ClientProduct
import com.example.kafkatest.model.ClientTargetAudiences
import java.util.*


interface ClientProfileRepository {

    fun findClientsByPhone(phone: String): List<Client?>

    fun findClientsByProfileId(profileId: UUID): List<Client>

    fun findClientProducts(profileId: UUID): List<ClientProduct?>

    fun findClientTargetAudiences(profileId: UUID): List<ClientTargetAudiences?>

    fun createClient(client: ClientDto)

    fun createProduct(product: CreateProductDto): CreateProductResponseDto

}