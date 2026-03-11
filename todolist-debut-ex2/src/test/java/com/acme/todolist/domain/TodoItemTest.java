package com.acme.todolist.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoItemTest {

    @Test
    void shouldContainsLateWhenItemIsLateOf24Hours() {
        Instant moreThanOneDayAgo = Instant.now().minus(25, ChronoUnit.HOURS);
        TodoItem item = new TodoItem("1", moreThanOneDayAgo, "Faire les courses");

        assertEquals("[LATE!] Faire les courses", item.finalContent());
    }

    @Test
    void shouldNotContainsLateBecauseItemIsInRangeTime() {
        Instant withinOneDay = Instant.now().minus(5, ChronoUnit.HOURS);
        TodoItem item = new TodoItem("2", withinOneDay, "Faire les courses");

        assertEquals("Faire les courses", item.finalContent());
    }
}
