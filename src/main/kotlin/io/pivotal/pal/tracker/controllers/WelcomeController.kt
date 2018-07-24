package io.pivotal.pal.tracker.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController(
        @Value("\${WELCOME_MESSAGE}") var message: String
) {
    @GetMapping("/")
    fun sayHello(): String {
        return message
    }
}
