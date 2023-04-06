package com.example.kafkatest.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.serializer.JsonDeserializer


//@EnableKafka
//@Configuration
//class KafkaConsumerConfig(
//    @Value("\${kafka.bootstrapAddress}")
//    private val servers: String
//) {
//
//    @Bean
//    fun consumerFactory(): ConsumerFactory<Long?, Any?> {
//        val props: MutableMap<String, Any> = HashMap()
//        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
//        props[ConsumerConfig.GROUP_ID_CONFIG] = "ppr"
//        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class.java
//        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = Deserializer::class.java
//        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
//        return DefaultKafkaConsumerFactory(props)
//    }
//
//    @Bean
//    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<Long, Any>? {
//        val factory = ConcurrentKafkaListenerContainerFactory<Long, Any>()
//        factory.consumerFactory = consumerFactory()
//        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
//        factory.containerProperties.isSyncCommits = true
//        factory.isBatchListener = true
//        factory.setMessageConverter(BatchMessagingMessageConverter(converter()))
//        return factory
//    }
//
//    @Bean
//    fun converter(): StringJsonMessageConverter? {
//        return StringJsonMessageConverter()
//    }
//}