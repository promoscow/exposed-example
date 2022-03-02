package ru.xpendence.exposed.repository.mapper

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import ru.xpendence.exposed.model.User
import ru.xpendence.exposed.repository.entity.ContactEntity
import ru.xpendence.exposed.repository.entity.UserEntity

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
fun ResultRow.toUser(): User = User(
    id = this[UserEntity.id].value,
    name = this[UserEntity.name],
    contacts = this.let {
        ContactEntity.select { ContactEntity.userId eq it[UserEntity.id].value }.map { it.toContact() }
    }
)