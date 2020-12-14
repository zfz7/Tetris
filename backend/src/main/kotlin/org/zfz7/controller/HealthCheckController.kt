package org.zfz7.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.zfz7.exchange.HelloWorldDTO
import java.time.Instant

@RestController
@RequestMapping("/api/health")
class HealthCheckController {

    @GetMapping()
    fun helloWorld(): String {
        return "We seem to be running good"
    }
}