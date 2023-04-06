package com.example.kafkatest.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.LongSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.serializer.JsonSerializer


//@Configuration
//class KafkaProducerConfig(
//
//    @Value("\${kafka.bootstrapAddress}")
//    private val servers: String
//) {
//    @Bean
//    fun producerFactory(): ProducerFactory<Long, Any> {
//        val configProps: MutableMap<String, Any> = HashMap()
//        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
//        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = LongSerializer::class.java
//        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = Serializer::class.java
//        return DefaultKafkaProducerFactory(configProps)
//    }
//
//    @Bean
//    fun kafkaTemplate(): KafkaTemplate<Long, Any> {
//        val template: KafkaTemplate<Long, Any> = KafkaTemplate(producerFactory())
//        template.messageConverter = StringJsonMessageConverter()
//        return template
//    }
//}