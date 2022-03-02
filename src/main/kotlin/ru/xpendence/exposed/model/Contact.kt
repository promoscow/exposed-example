package ru.xpendence.exposed.model

import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
data class Contact(
    val id: UUID? = null,
    val type: ContactType,
    val value: String? = null,
    val userId: UUID
)
