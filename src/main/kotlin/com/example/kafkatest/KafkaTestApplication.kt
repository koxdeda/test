package com.example.kafkatest


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class KafkaTestApplication

fun main(args: Array<String>) {

	// test main
	runApplication<KafkaTestApplication>(*args)


}
