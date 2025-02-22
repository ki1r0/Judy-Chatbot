package Judy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Judy.task.Todo;

public class TodoTest {
    @Test
    public void toString_test() {
        assertEquals("[T][ ] 123", new Todo("123").toString());

    }

    @Test
    public void toDataString_test() {
        assertEquals("T | 0 | 123", new Todo("123").toDataString());
    }
}
