package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import ru.xpendence.exposed.model.Contact
import ru.xpendence.exposed.repository.ContactRepository
import ru.xpendence.exposed.repository.entity.ContactEntity
import ru.xpendence.exposed.repository.mapper.toContact
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
        ContactEntity.insert {
            it[type] = contact.type.name
            it[value] = contact.value
            it[userId] = contact.userId
        }
            .resultedValues?.first()?.toContact()
            ?: throw NoSuchElementException("Error saving user: ${objectMapperKt.writeValueAsString(contact)}")
    }

    override fun get(id: UUID): Contact? = transaction {
        ContactEntity.select { ContactEntity.id eq id }
            .map { it.toContact() }
            .firstOrNull()
    }

    override fun getAll(userId: UUID): List<Contact> = transaction {
        ContactEntity.select { ContactEntity.userId eq userId }
            .map { it.toContact() }
    }

    override fun delete(id: UUID) {
        transaction {
            ContactEntity.deleteWhere { ContactEntity.id eq id }
        }
    }
}