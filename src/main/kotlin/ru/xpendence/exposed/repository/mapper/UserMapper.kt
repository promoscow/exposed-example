package ru.xpendence.exposed.repository.mapper

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import ru.xpendence.exposed.model.User
import ru.xpendence.exposed.repository.entity.ContactTable
import ru.xpendence.exposed.repository.entity.UserTable
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
fun ResultRow.toUser(): User = User(
    id = this[UserTable.id].value,
    name = this[UserTable.name],
    contacts = this[UserTable.id].value.let {
        ContactTable.select { ContactTable.userId eq it }.map { it.toContact() }
    }
)

fun Map.Entry<Column<EntityID<UUID>>, List<ResultRow>>.toUser(): User = let {
    val userResultRow = this.value.first()
    User(
        id = userResultRow[UserTable.id].value,
        name = userResultRow[UserTable.name],
        contacts = this.value
            .filter { it[ContactTable.id] != null }
            .map { it.toContact() }
    )
}