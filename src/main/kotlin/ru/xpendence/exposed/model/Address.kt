package ru.xpendence.exposed.model

import java.util.*

/**
 * Краткое описание класса.
 */
data class Address(
    val id: UUID? = null,
    val country: String,
    val city: String,
    val house: String,
    val building: String,
    val flat: String
)