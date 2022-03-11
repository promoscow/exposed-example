package ru.xpendence.exposed.repository.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.exposed.AbstractTest
import ru.xpendence.exposed.EntityGenerator
import ru.xpendence.exposed.repository.UserRepository

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
internal class UserRepositoryTest : AbstractTest() {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var entityGenerator: EntityGenerator

    @Test
    fun insert() {
        val user = entityGenerator.generateUser()
        assertDoesNotThrow { userRepository.insert(user) }
    }

    @Test
    fun get() {
        val user = entityGenerator.insertUser()
        assertNotNull(userRepository.get(user.id!!))
    }

    @Test
    fun getByJoin() {
        val user = entityGenerator.insertUser()
        entityGenerator.insertContact(user.id!!)
        entityGenerator.insertContact(user.id!!)
        entityGenerator.insertContact(user.id!!)
        val stored = userRepository.getByJoin(user.id!!)
        assertNotNull(stored)
        stored
            ?.also { s ->
                s.contacts
                    ?.also { c -> assertEquals(3, c.size) }
                    ?.forEach { assertEquals(user.id, it.userId) }
            }
    }

    @Test
    fun getAll() {
        repeat(3) { entityGenerator.insertUser() }
        assertEquals(2, userRepository.getAll(2).size)
    }

    @Test
    fun delete() {
        val user = entityGenerator.insertUser()
        userRepository.delete(user.id!!)
        assertNull(userRepository.get(user.id!!))
    }
}