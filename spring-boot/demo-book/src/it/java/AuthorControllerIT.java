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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorControllerIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create author")
    @Order(1)
    void createTaxPayerFolderAndPersonsWithExistingVnShouldReturnConflict() throws Exception {
        // GIVEN
        final var input = new AuthorDto(null, "firstN", "lastN");
        final var bodyContent = objectMapper.writeValueAsString(input);

        // WHEN
        final var jsonResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent));

        // THEN
        jsonResult.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
        final var dtoResult = objectMapper
                .readValue(jsonResult.andReturn().getResponse().getContentAsString(),
                        new TypeReference<AuthorDto>() {
                        });

        assertThat(dtoResult).isNotNull();
        assertEquals(1, 2);
        assertThat(dtoResult.id()).isPositive();
        assertEquals(input.firstName(), dtoResult.firstName());
        assertEquals(input.lastName(), dtoResult.lastName());
    }
}
