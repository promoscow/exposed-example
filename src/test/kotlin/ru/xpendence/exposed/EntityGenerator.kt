package ru.xpendence.exposed

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.xpendence.exposed.model.Contact
import ru.xpendence.exposed.model.ContactType
import ru.xpendence.exposed.model.User
import ru.xpendence.exposed.repository.ContactRepository
import ru.xpendence.exposed.repository.UserRepository
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
@Component
class EntityGenerator {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var contactRepository: ContactRepository

    fun generateUser(): User = User(
        name = RandomStringUtils.randomAlphanumeric(32)
    )

    fun insertUser(): User {
        val user = generateUser()
        return userRepository.insert(user)
    }

    fun generateContact(userId: UUID): Contact = Contact(
        type = ContactType.PHONE,
        value = RandomStringUtils.randomAlphanumeric(32),
        userId = userId
    )

    fun insertContact(userId: UUID): Contact {
        val contact = generateContact(userId)
        return contactRepository.insert(contact)
    }
}