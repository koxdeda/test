package com.example.kafkatest.exception


open class BaseException(

    override val message: String,
): RuntimeException(message)