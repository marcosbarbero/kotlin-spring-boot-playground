package com.marcosbarbero.kotlin.playground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootPlaygroundApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringBootPlaygroundApplication>(*args)
}
