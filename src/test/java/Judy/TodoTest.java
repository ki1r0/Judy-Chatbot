package Judy;
import Judy.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
