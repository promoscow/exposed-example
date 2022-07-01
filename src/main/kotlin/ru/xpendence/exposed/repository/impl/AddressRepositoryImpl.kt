package ru.xpendence.exposed.repository.impl

import org.springframework.stereotype.Repository
import ru.xpendence.exposed.model.Address
import ru.xpendence.exposed.repository.AddressRepository
import ru.xpendence.exposed.repository.entity.AddressEntity
import ru.xpendence.exposed.repository.mapper.toAddress
import java.util.*

/**
 * Краткое описание класса.
 */
@Repository
class AddressRepositoryImpl : AddressRepository {

    override fun insert(address: Address): Address =
        AddressEntity.new {
            country = address.country
            city = address.city
            house = address.house
            building = address.building
            flat = address.flat
        }
            .toAddress()

    override fun find(id: UUID): Address? = AddressEntity.findById(id)?.toAddress()

    override fun findAll(): List<Address> = AddressEntity.all().map { it.toAddress() }
}