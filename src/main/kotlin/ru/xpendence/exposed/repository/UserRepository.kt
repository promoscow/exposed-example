package ru.xpendence.exposed.repository

import ru.xpendence.exposed.model.User
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
interface UserRepository {

    fun insert(user: User): User

    fun update(user: User): User

    fun get(id: UUID): User?

    fun getByJoin(id: UUID): User?

    fun getAll(limit: Int): List<User>

    fun delete(id: UUID)
}