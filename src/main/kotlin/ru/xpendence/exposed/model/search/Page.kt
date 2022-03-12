package ru.xpendence.exposed.model.search

/**
 * Created: 12.03.2022
 * Author: Вячеслав Чернышов
 * email: vyacheslav.chernyshov@domrf.ru
 */
data class Page<T>(
    val content: List<T>,
    val page: Int,
    val size: Int,
    val totalElements: Long
)