package com.example.kafkatest.controllers

import com.example.kafkatest.dtos.ClientDto
import com.example.kafkatest.dtos.CreateProductDto
import com.example.kafkatest.dtos.CreateProductResponseDto
import com.example.kafkatest.dtos.KafkaTestDto
import com.example.kafkatest.fileReader.FileReader
import com.example.kafkatest.model.Client
import com.example.kafkatest.model.ClientProduct
import com.example.kafkatest.model.ClientTargetAudiences
import com.example.kafkatest.repository.ClientProfileRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/kafka")
class HelloController(@Value("\${kafka.topics.kafka-test}") val topic: String,
                      @Autowired
                      private val kafkaTemplate: KafkaTemplate<Long, Any>,
                      @Autowired
                      private val clientProfileRepository: ClientProfileRepository,
                      @Autowired
                      private val fileReader: FileReader
){

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun kafkaTest(){
        return clientProfileRepository.createClient(ClientDto(
            loyaltyProgram = "VTB",
            firstName = "TEST",
            lastName = "TEST",
            middleName = "TEST",
            email = "test@test.com",
            dateOfBirth = null,
            sex = null,
            phone = "4359805"
        ))
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    fun kafkaTest(@RequestBody message: CreateProductDto): CreateProductResponseDto {
        return clientProfileRepository.createProduct(message)

    }

    @GetMapping("/phone")
    @ResponseStatus(HttpStatus.OK)
    fun findClientByPhone(@RequestParam profileId: UUID): List<ClientTargetAudiences?> {
        return clientProfileRepository.findClientTargetAudiences(profileId)
    }

    fun sendKafkaTest(message: ClientDto) {
        try {
            log.info("Sending message to Kafka {}", message)
            kafkaTemplate.send(topic, 1, message)

            log.info("Message sent with success")
        } catch (e: Exception) {
            log.error("Exception: $e")
        }
    }

}

