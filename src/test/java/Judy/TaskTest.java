package Judy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import Judy.ui.Parser;
import Judy.util.JudyException;

public class TaskTest {

    @Test
    public void parseDateTime_validSingleDate() throws JudyException {
        assertEquals("Oct 2 1990", Parser.parseDateTime("02/10/1990 1800"));
    }

    @Test
    public void parseDateTime_validRange() throws JudyException {
        assertEquals("Oct 2 1990 - Oct 3 1990", Parser.parseDateTime("02/10/1990 1800", "03/10/1990 1200"));
    }

    @Test
    public void parseDateTime_invalidFormat_shouldThrowException() {
        Exception exception = assertThrows(JudyException.class, () -> {
            Parser.parseDateTime("1990/10/2 1800");
        });
        assertEquals("Function accepts either one or two date arguments.", exception.getMessage());
    }

    @Test
    public void parseDateTime_emptyInput_shouldThrowException() {
        Exception exception = assertThrows(JudyException.class, () -> {
            Parser.parseDateTime();
        });
        assertEquals("Function accepts either one or two date arguments.", exception.getMessage());
    }

    @Test
    public void parseDateTime_excessArguments_shouldThrowException() {
        Exception exception = assertThrows(JudyException.class, () -> {
            Parser.parseDateTime("02/10/1990 1800", "03/10/1990 1200", "04/10/1990 1500");
        });
        assertEquals("Function accepts either one or two date arguments.", exception.getMessage());
    }
}
