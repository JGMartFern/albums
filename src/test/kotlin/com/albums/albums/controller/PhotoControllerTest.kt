package com.albums.albums.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class PhotoControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    val baseUrl = "/api/photos"

    @Test
    fun `should return all photos with a given albumId`() {
        val albumId = 2
        mockMvc.get("$baseUrl?albumId=$albumId")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].title") {
                    value("non sunt voluptatem placeat consequuntur rem incidunt")
                }
            }
    }

    @Test
    fun `should return NOT FOUND if there are no albums with such id`() {
        val albumId = 101

        mockMvc.get("$baseUrl?albumId=$albumId")
            .andExpect { status { isNotFound() } }
    }

    @Test
    fun `should return BAD REQUEST if no positive integers are used`() {
        val albumId = 0

        mockMvc.get("$baseUrl?albumId=$albumId")
            .andExpect { status { isBadRequest() } }
    }
}