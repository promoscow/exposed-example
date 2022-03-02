package ru.xpendence.exposed.config

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

/**
 * Created: 02.03.2022
 * Author: Вячеслав Чернышов
 * email: slava_rossii@list.ru
 */
@Configuration
@EnableTransactionManagement
class ExposedConfig {

    /**
     * Замена бина необходима, если мы хотим использовать спринговое подключение к базе
     * вместо объявления подключения к базе данных в методах репозитория.
     */
    @Bean
    fun springTransactionManager(datasource: DataSource) = SpringTransactionManager(datasource)
}
