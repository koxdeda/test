package com.example.kafkatest.config

import com.example.kafkatest.dtos.ClientDto
import com.example.kafkatest.dtos.KafkaList
import com.example.kafkatest.dtos.KafkaTestDto
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import kotlin.text.Charsets.UTF_8

class Deserializer: Deserializer<ClientDto> {
    private val objectMapper = com.fasterxml.jackson.module.kotlin.jacksonObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): ClientDto? {
        log.info("Deserializing...")
        return objectMapper.readValue(
            String(
                data ?: throw SerializationException("Error when deserializing byte[] to KafkaTestDto"), UTF_8
            ), ClientDto::class.java
        )
    }

    override fun close() {}

}