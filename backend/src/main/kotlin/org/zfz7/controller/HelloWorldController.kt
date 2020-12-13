package org.zfz7.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.zfz7.exchange.HelloWorldDTO
import java.time.Instant

@RestController
@RequestMapping("/api/hello")
class HelloWorldController {

    @GetMapping()
    fun helloWorld(): HelloWorldDTO {
        return HelloWorldDTO(message = "Hello, the time at the server is ${Instant.now()}")
    }
}