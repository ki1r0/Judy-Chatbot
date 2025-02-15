package Judy;

import Judy.ui.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void parseDateTime_test() {
        assertEquals("Oct 2 1990", Parser.parseDateTime("02/10/1990 1800"));
    }

    @Test
    public void parseDateTime_fail() {
        assertEquals(null, Parser.parseDateTime("1990/10/2 1800"));
    }
}
