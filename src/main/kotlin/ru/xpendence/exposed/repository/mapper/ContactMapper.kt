package ru.xpendence.exposed.repository.mapper

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import ru.xpendence.exposed.model.Contact
import ru.xpendence.exposed.model.ContactType
import ru.xpendence.exposed.repository.entity.ContactTable

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
fun ResultRow.toContact(): Contact = Contact(
    id = this[ContactTable.id].value,
    type = ContactType.valueOf(this[ContactTable.type]),
    value = this[ContactTable.value],
    userId = this[ContactTable.userId].value
)

fun Contact.toInsertStatement(statement: InsertStatement<Number>): InsertStatement<Number> = statement.also {
    it[ContactTable.type] = this.type.name
    it[ContactTable.value] = this.value
    it[ContactTable.userId] = this.userId
}

fun Contact.toUpdateStatement(statement: UpdateStatement): UpdateStatement = statement.also {
    it[ContactTable.type] = this.type.name
    it[ContactTable.value] = this.value
    it[ContactTable.userId] = this.userId
}
