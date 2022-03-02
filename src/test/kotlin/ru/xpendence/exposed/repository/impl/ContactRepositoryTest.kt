package ru.xpendence.exposed.repository.impl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.exposed.AbstractTest
import ru.xpendence.exposed.EntityGenerator
import ru.xpendence.exposed.repository.ContactRepository

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: vyacheslav.chernyshov@domrf.ru
 */
internal class ContactRepositoryTest : AbstractTest() {

    @Autowired
    private lateinit var contactRepository: ContactRepository

    @Autowired
    private lateinit var entityGenerator: EntityGenerator

    @Test
    fun insert() {
        val user = entityGenerator.insertUser()
        val contact = entityGenerator.generateContact(user.id!!)
        assertDoesNotThrow { contactRepository.insert(contact) }
    }

    @Test
    fun get() {
        val user = entityGenerator.insertUser()
        val contact = entityGenerator.insertContact(user.id!!)
        assertNotNull(contactRepository.get(contact.id!!))
    }

    @Test
    fun getAll() {
        val user = entityGenerator.insertUser()
        val contact = entityGenerator.insertContact(user.id!!)
        assertEquals(1, contactRepository.getAll(contact.userId).size)
    }

    @Test
    fun delete() {
        val user = entityGenerator.insertUser()
        val contact = entityGenerator.insertContact(user.id!!)
        contactRepository.delete(contact.id!!)
        assertNull(contactRepository.get(contact.id!!))
    }
}