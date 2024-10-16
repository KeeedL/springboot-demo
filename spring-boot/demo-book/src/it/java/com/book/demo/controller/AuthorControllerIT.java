package com.book.demo.controller;

import com.book.demo.dto.AuthorDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.book.demo.constant.RestPathValue.AUTHOR_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorControllerIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private final AuthorDto firstAuthor = new AuthorDto(null, "Django", "Dupont");


    @Test
    @DisplayName("Create author")
    @Order(1)
void createAuthorShouldReturnCreated() throws Exception {
        // GIVEN
        final var jsonAuthor = objectMapper.writeValueAsString(firstAuthor);

        // WHEN
        final var jsonResult = mockMvc.perform(MockMvcRequestBuilders
                .post(AUTHOR_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAuthor));

        // THEN
        jsonResult.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
        final var dtoResult = objectMapper
                .readValue(jsonResult.andReturn().getResponse().getContentAsString(),
                        new TypeReference<AuthorDto>() {
                        });

        assertThat(dtoResult).isNotNull();
        assertThat(dtoResult.id()).isPositive();
        assertEquals(firstAuthor.firstName(), dtoResult.firstName());
        assertEquals(firstAuthor.lastName(), dtoResult.lastName());

    }

    @Test
    @DisplayName("Get author created")
    @Order(2)
    void getAuthorCreatedShouldReturnOk() throws Exception {
        // GIVEN
        final var bodyContent = objectMapper.writeValueAsString(firstAuthor);

        // WHEN
        final var jsonResult = mockMvc.perform(MockMvcRequestBuilders
                .get(AUTHOR_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent));

        // THEN
        jsonResult.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
        final var dtoResult = objectMapper
                .readValue(jsonResult.andReturn().getResponse().getContentAsString(),
                        new TypeReference<List<AuthorDto>>() {
                        });

        assertThat(dtoResult).isNotNull();
        assertEquals(1, dtoResult.size());
        final var authorResponse = dtoResult.stream().findFirst();
        assertTrue(authorResponse.isPresent());
        assertThat(authorResponse.get().id()).isPositive();
        assertEquals(firstAuthor.firstName(), authorResponse.get().firstName());
        assertEquals(firstAuthor.lastName(), authorResponse.get().lastName());
    }
}
