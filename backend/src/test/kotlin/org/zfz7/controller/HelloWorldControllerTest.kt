package org.zfz7.controller
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.zfz7.exchange.HelloWorldDTO

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:setup.sql")
class HelloWorldControllerTest {
  @Autowired
  private lateinit var mockMvc: MockMvc

  @Autowired
  private lateinit var jackson2ObjectMapperBuilder: Jackson2ObjectMapperBuilder

  private lateinit var objectMapper: ObjectMapper

  @BeforeEach
  fun setup() {
    objectMapper = jackson2ObjectMapperBuilder.build()
  }

  @Test
  @DisplayName("Get Hello World Message")
  fun `Should return correct hello world message`() {
    var result = mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk)
        .andReturn()

    var response = objectMapper.readValue(result.response.contentAsByteArray, HelloWorldDTO::class.java)
    assertEquals(response.message,"Hello, you are visitor: 0")

    result = mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andReturn()

    response = objectMapper.readValue(result.response.contentAsByteArray, HelloWorldDTO::class.java)
    assertEquals(response.message,"Hello, you are visitor: 1")
  }

}
