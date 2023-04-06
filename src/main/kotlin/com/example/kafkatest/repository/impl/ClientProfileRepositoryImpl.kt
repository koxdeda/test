package com.example.kafkatest.repository.impl

import com.example.kafkatest.dtos.ClientDto
import com.example.kafkatest.dtos.CreateProductDto
import com.example.kafkatest.dtos.CreateProductResponseDto
import com.example.kafkatest.exception.ClientNotFoundException
import com.example.kafkatest.model.Client
import com.example.kafkatest.model.ClientProduct
import com.example.kafkatest.model.ClientTargetAudiences
import com.example.kafkatest.repository.ClientProfileRepository
import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.*


@Repository
class ClientProfileRepositoryImpl(
    @Autowired
    val jdbcTemplate: JdbcTemplate
): ClientProfileRepository {

    // queryForObject for requests with single object in result
    override fun findClientsByPhone(phone: String): List<Client?> {
        try{
        val client = jdbcTemplate.query(
            "select * from client_profile where phone = ?",
            arrayOf(phone)) { rs: ResultSet, _ ->
            Client(
                id = rs.getString("id"),
                client_id = rs.getString("client_id"),
                loyaltyProgram = rs.getString("loyalty_program"),
                firstName = rs.getString("first_name"),
                lastName = rs.getString("last_name"),
                middleName = rs.getString("middle_name"),
                dateOfBirth = rs.getString("date_of_birth"),
                sex = rs.getString("sex"),
                status = rs.getString("status"),
                phone = rs.getString("phone"),
                email = rs.getString("email")
            )
        }
            return client
        } catch (e: IncorrectResultSizeDataAccessException) {
            return listOf()
        }
    }

    // query for requests with list of objects in result
    override fun findClientsByProfileId(profileId: UUID): List<Client> {
        try{
            val clients = jdbcTemplate.query(
                "select * from client_profile where id= ?",
                arrayOf(profileId)) { rs: ResultSet, _ ->
                Client(
                    id = rs.getString("id"),
                    client_id = rs.getString("client_id"),
                    loyaltyProgram = rs.getString("loyalty_program"),
                    firstName = rs.getString("first_name"),
                    lastName = rs.getString("last_name"),
                    middleName = rs.getString("middle_name"),
                    dateOfBirth = rs.getString("date_of_birth"),
                    sex = rs.getString("sex"),
                    status = rs.getString("status"),
                    phone = rs.getString("phone"),
                    email = rs.getString("email")
                )
            }
            return clients
        } catch (e: IncorrectResultSizeDataAccessException) {
            return listOf()
        }
    }


    override fun findClientProducts(profileId: UUID): List<ClientProduct?> {
        try {
            val products = jdbcTemplate.query(
                "select * from client_products where profile_id = ?", arrayOf(profileId)
            ) {
                rs: ResultSet, _ ->
                ClientProduct(
                    id = rs.getString("id"),
                    profileId = rs.getString("profile_id"),
                    productType = rs.getString("product_type"),
                    lastDigits = rs.getString("last_digits"),
                    cardAccount = rs.getString("card_account")
                )
            }
            return products
        }catch (e: IncorrectResultSizeDataAccessException) {
            return listOf()
        }
    }

    override fun findClientTargetAudiences(profileId: UUID): List<ClientTargetAudiences?> {
        try {
            val targetAudiences = jdbcTemplate.query(
                "select * from target_audience_client where profile_id = ?", arrayOf(profileId)
            ){
                rs: ResultSet, _ ->
                ClientTargetAudiences(
                    id = rs.getString("id"),
                    profileId = rs.getString("profile_id"),
                    targetAudiences = rs.getString("target_audiences"),
                    source = rs.getString("source")
                )
            }
            return targetAudiences
        }catch (e: IncorrectResultSizeDataAccessException) {
            return listOf()
        }
    }

    override fun createClient(client: ClientDto){
        val query = "INSERT INTO client_profile (id, client_id, loyalty_program, first_name, phone, updated_At, updated_At_Tz) VALUES (?,?,?,?,?,?,?)"
        val datetime = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
        val mDate = sdf.format(datetime)

        val timezone = ZonedDateTime.now()

        val offsetDateTime = OffsetDateTime.now()
        val localDateTime = LocalDateTime.now()

        //2023-03-20T17:32:51.995427300Z
        timezone.toInstant()

        try {
            println("Start bd")
            jdbcTemplate.execute(query) { ps: PreparedStatement ->
                ps.setObject(1, UUID.randomUUID())
                ps.setObject(2, UUID.randomUUID())
                ps.setString(3, client.loyaltyProgram)
                ps.setString(4, client.firstName)
                ps.setString(5, client.phone)
                ps.setObject(6, localDateTime)
                ps.setObject(7, offsetDateTime)
                ps.execute()
            }
        }catch (e: JsonProcessingException){
            println(e.message)
        }
    }

    override fun createProduct(product: CreateProductDto): CreateProductResponseDto {
        val query =
            "INSERT INTO client_products (id, profile_id, product_type, last_digits, card_account) VALUES (?,?,?,?,?)"
        val productData = product.product.split(":").map { it.trim() }
        if (findClientsByProfileId(product.profileId).isEmpty()) {
            throw ClientNotFoundException("profileId")
        } else {
            try {
                jdbcTemplate.execute(query) { ps: PreparedStatement ->
                    ps.setObject(1, UUID.randomUUID())
                    ps.setObject(2, product.profileId)
                    ps.setString(3, productData[0])
                    ps.setString(4, productData[1])
                    ps.setString(5, productData[2])
                    ps.execute()
                }
            } catch (e: JsonProcessingException) {
                println(e.message)
            }
            return CreateProductResponseDto("success")
        }
    }
}