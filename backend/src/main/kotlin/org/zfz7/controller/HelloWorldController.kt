package org.zfz7.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.zfz7.exchange.HelloWorldDTO
import org.zfz7.repository.HelloWorldRepository

@RestController
@RequestMapping("/api/hello")
class HelloWorldController(
    val helloWorldRepository: HelloWorldRepository
) {

    @GetMapping()
    fun helloWorld(): HelloWorldDTO {
        val helloWord = helloWorldRepository.findFirstByOrderByIdDesc()
        val dto =  HelloWorldDTO(message = "Hello, you are visitor: ${helloWord.visit}")
        helloWorldRepository.save(helloWord.copy(visit = helloWord.visit+1))
        return dto
    }
}