package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import ru.xpendence.exposed.model.Contact
import ru.xpendence.exposed.repository.ContactRepository
import ru.xpendence.exposed.repository.entity.ContactTable
import ru.xpendence.exposed.repository.mapper.toContact
import ru.xpendence.exposed.repository.mapper.toInsertStatement
import ru.xpendence.exposed.repository.mapper.toUpdateStatement
import ru.xpendence.exposed.util.objectMapperKt
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
@Repository
class ContactRepositoryImpl : ContactRepository {

    override fun insert(contact: Contact): Contact = transaction {
        ContactTable.insert { contact.toInsertStatement(it) }
            .resultedValues?.first()?.toContact()
            ?: throw NoSuchElementException("Error saving user: ${objectMapperKt.writeValueAsString(contact)}")
    }

    override fun update(contact: Contact) {
        transaction {
            ContactTable.update({ ContactTable.id eq contact.id!! }) { contact.toUpdateStatement(it) }
        }
    }

    override fun get(id: UUID): Contact? = transaction {
        ContactTable.select { ContactTable.id eq id }
            .firstOrNull()?.toContact()
    }

    override fun getAll(userId: UUID): List<Contact> = transaction {
        ContactTable.select { ContactTable.userId eq userId }
            .map { it.toContact() }
    }

    override fun delete(id: UUID) {
        transaction {
            ContactTable.deleteWhere { ContactTable.id eq id }
        }
    }
}