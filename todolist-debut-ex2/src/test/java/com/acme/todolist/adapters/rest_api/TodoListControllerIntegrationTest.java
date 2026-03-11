package com.acme.todolist.adapters.rest_api;

import com.acme.todolist.adapters.persistence.entity.TodoItemJpaEntity;
import com.acme.todolist.adapters.persistence.repository.TodoItemRepository;
import com.acme.todolist.domain.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TodoListControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoItemRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test
    void postTodoItemPersistsItAndReturnsCreatedStatus() {

        // Given
        TodoItem requestItem = new TodoItem("0f8-06eb17ba8d34", Instant.now(), "Faire les courses");

        // When
        ResponseEntity<Void> response = restTemplate.postForEntity("/todos", requestItem, Void.class);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        List<TodoItemJpaEntity> storedItems = repository.findAll();

        assertEquals(1, storedItems.size());

        TodoItemJpaEntity stored = storedItems.get(0);

        assertEquals(requestItem.getId(), stored.getId());

        assertEquals(requestItem.getContent(), stored.getContent());

        assertEquals(requestItem.getTime(), stored.getTime());

        assertEquals(Boolean.TRUE, stored.getVisible());
    }
}
