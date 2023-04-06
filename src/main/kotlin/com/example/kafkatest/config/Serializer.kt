package com.example.kafkatest.config


import com.example.kafkatest.dtos.ClientDto
import com.example.kafkatest.dtos.KafkaList
import com.example.kafkatest.dtos.KafkaTestDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory

class Serializer: Serializer<ClientDto> {

    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: ClientDto?): ByteArray? {
        log.info("Serializing...")
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Dto to ByteArray[]")
        )
    }

    override fun close() {}
}
