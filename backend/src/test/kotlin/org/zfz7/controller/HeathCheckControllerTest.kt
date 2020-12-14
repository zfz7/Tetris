package org.zfz7.controller
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.zfz7.exchange.HelloWorldDTO
import java.time.Instant
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
@SpringBootTest
@AutoConfigureMockMvc
class HeathCheckControllerTest {
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
  fun `Should return correct health check message`() {
    val now = Instant.now()
    mockkStatic(Instant::class)
    every { Instant.now() } returns now

    val result = mockMvc.perform(MockMvcRequestBuilders.get("/api/health")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk)
        .andReturn()

    assertEquals(result.response.contentAsString,"We seem to be running good")
  }

}
