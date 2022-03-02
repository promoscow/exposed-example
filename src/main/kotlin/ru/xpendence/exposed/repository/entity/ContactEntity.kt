package ru.xpendence.exposed.repository.entity

import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
object ContactEntity : IdTable<UUID>("contacts") {
    override val id = ContactEntity.uuid("id").entityId()
    val type = varchar("type", 128)
    val value = varchar("value", 256).nullable()
    val userId = reference("user_id", UserEntity)
}