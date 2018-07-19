package io.pivotal.pal.tracker

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @GetMapping("/")
    fun sayHello(): String {
        return "hello"
    }
}
