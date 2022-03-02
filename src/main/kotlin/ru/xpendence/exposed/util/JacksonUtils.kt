package ru.xpendence.exposed.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.openapitools.jackson.nullable.JsonNullableModule

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
val objectMapperKt: ObjectMapper = jacksonObjectMapper()
    .registerModule(JavaTimeModule())
    .registerModule(JsonNullableModule())
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)