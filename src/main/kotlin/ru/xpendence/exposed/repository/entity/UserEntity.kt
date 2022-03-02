package ru.xpendence.exposed.repository.entity

import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
object UserEntity : IdTable<UUID>("users") {
    override val id = uuid("id").entityId()
    val name = varchar("name", 512).nullable()
}