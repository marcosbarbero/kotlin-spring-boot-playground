package com.marcosbarbero.kotlin.playground

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OptionalBeanConfig {

    companion object {
        private val log = LoggerFactory.getLogger(OptionalBeanConfig::class.java)
    }

    @ConditionalOnProperty(prefix = "app.properties", name = ["enabled"], havingValue = "true")
    @EnableConfigurationProperties(Enabled.OptionalConfigurationProperties::class)
    class Enabled {

        @ConfigurationProperties(prefix = "app.properties")
        data class OptionalConfigurationProperties(var enabled: String)

        @Bean
        fun enabledAppProperties(properties: OptionalConfigurationProperties):
                OptionalBean = OptionalBean(properties.enabled)
            .also { log.info("Bean is enabled.") }
    }

    @ConditionalOnProperty(prefix = "app.properties", name = ["enabled"], havingValue = "false", matchIfMissing = true)
    class NotEnabled {
        @Bean
        fun defaultAppProperties(): OptionalBean = OptionalBean("false")
            .also { log.info("Bean is NOT enabled.") }

    }
}

class OptionalBean(val enabled: String)