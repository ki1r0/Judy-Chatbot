package Judy.task;

import Judy.ui.Parser;

public class Event extends Task {
    private final String period;

    public Event(String description, String start, String end) {
        super(description);
        this.period = Parser.parseDateTime(start, end);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + period + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + period;
    }
}