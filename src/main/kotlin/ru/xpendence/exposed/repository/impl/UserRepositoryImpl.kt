package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.xpendence.exposed.model.User
import ru.xpendence.exposed.repository.UserRepository
import ru.xpendence.exposed.repository.entity.ContactTable
import ru.xpendence.exposed.repository.entity.UserTable
import ru.xpendence.exposed.repository.mapper.toUser
import ru.xpendence.exposed.util.objectMapperKt
import java.util.*

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
@Repository
class UserRepositoryImpl : UserRepository {

    override fun insert(user: User): User = transaction {
        UserTable.insert {
            it[name] = user.name
        }
            .resultedValues?.first()?.toUser()
            ?: throw NoSuchElementException(
                "Error saving user: ${objectMapperKt.writeValueAsString(user)}. Statement result is null."
            )
    }

    override fun update(user: User): User = transaction {
        UserTable.update({ UserTable.id eq user.id }) {
            it[name] = user.name
        }
        UserTable.select { UserTable.id eq user.id }
            .firstOrNull()?.toUser()
            ?: throw NoSuchElementException(
                "Error updating user: ${objectMapperKt.writeValueAsString(user)}. Statement result is null."
            )
    }

    override fun get(id: UUID): User? = transaction {
        UserTable.select { UserTable.id eq id }.firstOrNull()?.toUser()
    }

    override fun getByJoin(id: UUID): User? = transaction {
        (UserTable leftJoin ContactTable)
            .select { UserTable.id eq id }
            .groupBy { UserTable.id }
            .entries.firstOrNull()?.toUser()
    }

    override fun getAll(limit: Int): List<User> = transaction {
        UserTable.selectAll()
            .limit(limit)
            .map { it.toUser() }
    }

    @Transactional
    override fun delete(id: UUID) {
            UserTable.deleteWhere { UserTable.id eq id }
    }
}