package ru.xpendence.exposed.model

import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
data class User(
    val id: UUID? = null,
    val name: String? = null,
    val contacts: List<Contact>? = null
)
