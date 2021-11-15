package com.myshop.myshop.junit;

import com.myshop.myshop.entity.Todo;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTodoEntity {
    @Test
    public void whenGetId_ThenSetId() {
        Todo Todo = new Todo();
        Todo.setId(1);
        Integer expected = 1;
        Integer actual = Todo.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void whenGetTask_ThenSetTask() {
        Todo Todo = new Todo();
        Todo.setTask("洗衣服");
        String expected = "洗衣服";
        String actual = Todo.getTask();

        assertEquals(expected, actual);
    }

    @Test
    public void whenSetTask_ThenGetTask() {
        Todo Todo = new Todo();
        Todo.setStatus(2);
        Integer expected = 2;
        Integer actual = Todo.getStatus();

        assertEquals(expected, actual);
    }
}