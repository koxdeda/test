package com.example.kafkatest


import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.Scheduled



@SpringBootApplication
class KafkaTestApplication

fun main(args: Array<String>) {


	//test develop
	runApplication<KafkaTestApplication>(*args)


}
