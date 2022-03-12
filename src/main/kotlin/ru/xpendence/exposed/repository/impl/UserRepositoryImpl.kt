package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import ru.xpendence.exposed.model.User
import ru.xpendence.exposed.model.search.Page
import ru.xpendence.exposed.repository.UserRepository
import ru.xpendence.exposed.repository.entity.ContactEntity
import ru.xpendence.exposed.repository.entity.UserEntity
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
        UserEntity.insert {
            it[name] = user.name
        }
            .resultedValues?.first()?.toUser()
            ?: throw NoSuchElementException(
                "Error saving user: ${objectMapperKt.writeValueAsString(user)}. Statement result is null."
            )
    }

    override fun update(user: User): User = transaction {
        UserEntity.update({ UserEntity.id eq user.id }) {
            it[name] = user.name
        }
        UserEntity.select { UserEntity.id eq user.id }
            .firstOrNull()?.toUser()
            ?: throw NoSuchElementException(
                "Error updating user: ${objectMapperKt.writeValueAsString(user)}. Statement result is null."
            )
    }

    override fun get(id: UUID): User? = transaction {
        UserEntity.select { UserEntity.id eq id }
            .firstOrNull()?.toUser()
    }

    override fun getByJoin(id: UUID): User? = transaction {
        (UserEntity leftJoin ContactEntity)
            .slice(UserEntity.columns.plus(ContactEntity.columns))
            .select { UserEntity.id eq id }
            .groupBy { UserEntity.id }
            .entries.firstOrNull()?.toUser()
    }

    override fun getAll(limit: Int): List<User> = transaction {
        UserEntity.selectAll()
            .limit(limit)
            .map { it.toUser() }
    }

    override fun getPage(
        search: String?,
        orderBy: String,
        sort: SortOrder,
        page: Int,
        size: Int
    ): Page<User> = transaction {
        val query = search?.let {
            UserEntity
                .slice(UserEntity.id)
                .select { UserEntity.id eq UUID.fromString(it) }
                .orWhere { UserEntity.name like "%$search%" }
        } ?: UserEntity.slice(UserEntity.id).selectAll()
        val users = query
            .orderBy(UserEntity.columns.first { it.name == orderBy }, sort)
            .limit(size, (page * size).toLong())
            .mapNotNull {
                (UserEntity leftJoin ContactEntity)
                    .select { UserEntity.id eq it[UserEntity.id].value }
                    .groupBy { UserEntity.id }
                    .entries.firstOrNull()?.toUser()

            }
        Page(users, page, size, query.count())
    }

    override fun delete(id: UUID) {
        transaction {
            UserEntity.deleteWhere { UserEntity.id eq id }
        }
    }
}