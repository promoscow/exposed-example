package ru.xpendence.exposed.repository.mapper

import org.jetbrains.exposed.sql.ResultRow
import ru.xpendence.exposed.model.Contact
import ru.xpendence.exposed.model.ContactType
import ru.xpendence.exposed.repository.entity.ContactEntity

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
fun ResultRow.toContact(): Contact = Contact(
    id = this[ContactEntity.id].value,
    type = ContactType.valueOf(this[ContactEntity.type]),
    value = this[ContactEntity.value],
    userId = this[ContactEntity.userId].value
)