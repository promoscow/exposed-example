package ru.xpendence.exposed.repository.mapper

import org.jetbrains.exposed.dao.id.EntityID
import ru.xpendence.exposed.model.Address
import ru.xpendence.exposed.repository.entity.AddressEntity
import ru.xpendence.exposed.repository.entity.AddressTable

/**
 * Краткое описание класса.
 */
fun AddressEntity.toAddress(): Address = Address(
    id.value, country, city, house, building, flat
)

fun Address.toAddressEntity(): AddressEntity = AddressEntity(EntityID(id!!, AddressTable))
    .also {
        it.country = country
        it.city = city
        it.house = house
        it.building = building
        it.flat = flat
    }