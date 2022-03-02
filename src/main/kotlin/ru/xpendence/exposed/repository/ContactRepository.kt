package ru.xpendence.exposed.repository

import ru.xpendence.exposed.model.Contact
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
interface ContactRepository {

    fun insert(contact: Contact): Contact

    fun get(id: UUID): Contact?

    fun getAll(userId: UUID): List<Contact>

    fun delete(id: UUID)
}