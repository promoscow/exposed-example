package ru.xpendence.exposed.repository.impl

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import ru.xpendence.exposed.model.User
import ru.xpendence.exposed.repository.UserRepository
import ru.xpendence.exposed.repository.entity.UserEntity
import ru.xpendence.exposed.repository.mapper.toUser
import ru.xpendence.exposed.util.objectMapperKt
import java.util.*
import kotlin.NoSuchElementException

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
            ?: throw NoSuchElementException("Error saving user: ${objectMapperKt.writeValueAsString(user)}")
    }

    override fun get(id: UUID): User? = transaction {
        UserEntity.select { UserEntity.id eq id }
            .map { it.toUser() }
            .firstOrNull()
    }

    override fun getAll(limit: Int): List<User> = transaction {
        UserEntity.selectAll()
            .limit(limit)
            .map { it.toUser() }
    }

    override fun delete(id: UUID) {
        transaction {
            UserEntity.deleteWhere { UserEntity.id eq id }
        }
    }
}