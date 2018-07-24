package io.pivotal.pal.tracker.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EnvController(
        @Value("\${PORT:NOT SET}") var port: String,
        @Value("\${MEMORY_LIMIT:NOT SET}") var memoryLimit: String,
        @Value("\${CF_INSTANCE_INDEX:NOT SET}") var cfInstanceIndex: String,
        @Value("\${CF_INSTANCE_ADDR:NOT SET}") var cfInstanceAddr: String
) {

    @GetMapping("/env")
    fun getEnv(): Map<String, String> =
            hashMapOf(
                    "PORT" to port,
                    "MEMORY_LIMIT" to memoryLimit,
                    "CF_INSTANCE_INDEX" to cfInstanceIndex,
                    "CF_INSTANCE_ADDR" to cfInstanceAddr
            )
}