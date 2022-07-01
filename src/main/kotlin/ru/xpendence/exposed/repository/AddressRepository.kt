package ru.xpendence.exposed.repository

import ru.xpendence.exposed.model.Address
import java.util.*

/**
 * Краткое описание класса.
 */
interface AddressRepository {

    fun insert(address: Address): Address

    fun find(id: UUID): Address?

    fun findAll(): List<Address>
}