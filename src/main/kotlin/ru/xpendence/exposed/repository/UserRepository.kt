package ru.xpendence.exposed.repository

import org.jetbrains.exposed.sql.SortOrder
import ru.xpendence.exposed.model.User
import ru.xpendence.exposed.model.search.Page
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

    fun getPage(search: String?, orderBy: String, sort: SortOrder, page: Int, size: Int): Page<User>

    fun delete(id: UUID)
}