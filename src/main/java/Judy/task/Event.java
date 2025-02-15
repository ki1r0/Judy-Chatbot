package Judy.task;

import Judy.ui.Parser;

public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = Parser.parseDateTime(start);
        this.end = Parser.parseDateTime(end);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end +")";
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + start + " - " + end;
    }
}