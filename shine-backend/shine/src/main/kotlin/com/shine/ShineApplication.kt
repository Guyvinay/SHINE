package com.shine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShineApplication

fun main(args: Array<String>) {
	runApplication<ShineApplication>(*args)
}
