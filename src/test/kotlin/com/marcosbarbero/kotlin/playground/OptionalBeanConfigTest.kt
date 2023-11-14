package com.marcosbarbero.kotlin.playground

import com.marcosbarbero.kotlin.playground.OptionalBeanConfig.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class OptionalBeanConfigTest {

    private val contextRunner: ApplicationContextRunner =
        ApplicationContextRunner().withUserConfiguration(OptionalBeanConfig::class.java)

    @Test
    fun testBeanEnabled() {
        contextRunner.withPropertyValues("app.properties.enabled=true")
            .run {
                val properties: Enabled.OptionalConfigurationProperties =
                    it.getBean(Enabled.OptionalConfigurationProperties::class.java)
                val optionalBean: OptionalBean = it.getBean(OptionalBean::class.java)
                assertThat(properties).isNotNull
                assertThat(optionalBean.enabled).isEqualTo("true")
            }
    }

    @Test
    fun testBeanNotEnabled() {
        contextRunner.withPropertyValues("app.properties.enabled=false")
            .run {
                val optionalBean: OptionalBean = it.getBean(OptionalBean::class.java)
                assertThat(optionalBean.enabled).isEqualTo("false")
            }
    }
}