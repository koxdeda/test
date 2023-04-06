package com.example.kafkatest.workers

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

//@Component
//class ClientCreateWorker {
//
//
//    @KafkaListener(topics= ["\${kafka.topics.kafka-test}"], groupId = "\${kafka.group.id}")
//    fun msgListener(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
//        println("Message key is: ${consumerRecord.key()}")
//        println("Message partition is: ${consumerRecord.partition()}")
//        println("Message value is: ${consumerRecord.value()}")
//        ack.acknowledge()
//
//
//    }
//}