package ru.xpendence.exposed.repository.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * Краткое описание класса.
 */
object AddressTable : IdTable<UUID>("addresses") {
    override val id = uuid("id").entityId()
    val country = varchar("country", 512)
    val city = varchar("city", 512)
    val house = varchar("house", 64)
    val building = varchar("building", 64)
    val flat = varchar("flat", 64)
}

class AddressEntity(id: EntityID<UUID>) : Entity<UUID>(id) {

    companion object : EntityClass<UUID, AddressEntity>(AddressTable)

    var country by AddressTable.country
    var city by AddressTable.city
    var house by AddressTable.house
    var building by AddressTable.building
    var flat by AddressTable.flat
}