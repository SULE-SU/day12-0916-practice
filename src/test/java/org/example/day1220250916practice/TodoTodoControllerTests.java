package org.example.day1220250916practice;

import org.example.day1220250916practice.entity.Todo;
import org.example.day1220250916practice.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoTodoControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
    }

    @Test
    void should_response_empty_list_when_no_any_todos() throws Exception {
        MockHttpServletRequestBuilder request = get("/todos")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_response_one_todo_when_index_with_one_todo() throws Exception {
        Todo todo = new Todo(null, "Buy milk", false);

        todoRepository.save(todo);

        MockHttpServletRequestBuilder request = get("/todos")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].text").value("Buy milk"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void should_respond_201_when_create_todo_successfully() throws Exception {
        String requestBody = """
                {
                    "text": "Buy milk",
                    "done": false
                }
                """;

        MockHttpServletRequestBuilder request = post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());


    }

    @Test
    void should_respond_422_when_create_todo_with_empty_text() throws Exception {
        String requestBody = """
                {
                    "text": "",
                    "done": false
                }
                """;

        MockHttpServletRequestBuilder request = post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(request)
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void should_respond_422_when_create_todo_without_text_field() throws Exception {
        String requestBody = """
                {
                    "done": false
                }
                """;

        MockHttpServletRequestBuilder request = post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(request)
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void should_ignore_client_sent_id_and_generate_own_id() throws Exception {
        String requestBody = """
                {
                    "id": "client-sent",
                    "text": "Buy bread",
                    "done": false
                }
                """;

        MockHttpServletRequestBuilder request = post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(org.hamcrest.Matchers.not("client-sent")));
    }

    @Test
    void should_update_both_fields_when_put_todo() throws Exception {
        Todo existingTodo = new Todo("123", "Buy milk", false);
        todoRepository.save(existingTodo);
        String requestBody = """
                {
                    "id": "123",
                    "text": "Buy snacks",
                    "done": true
                }
                """;
        MockHttpServletRequestBuilder request = put("/todos" + "/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.text").value("Buy snacks"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void should_ignore_surplus_id_in_body_when_put_todo() throws Exception {
        Todo todo123 = new Todo("123", "Original text", false);
        Todo todo456 = new Todo("456", "Another text", false);
        todoRepository.save(todo123);
        todoRepository.save(todo456);

        String requestBody = """
                {
                    "id": "456",
                    "text": "Buy snacks",
                    "done": true
                }
                """;
        MockHttpServletRequestBuilder request = put("/todos" + "/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.text").value("Buy snacks"))
                .andExpect(jsonPath("$.done").value(true));

    }
}
