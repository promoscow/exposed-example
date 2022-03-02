package ru.xpendence.exposed.repository.impl

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.xpendence.exposed.AbstractTest
import ru.xpendence.exposed.EntityGenerator
import ru.xpendence.exposed.model.User
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