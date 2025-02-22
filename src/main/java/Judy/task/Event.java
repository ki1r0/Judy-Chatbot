package Judy.task;

import Judy.ui.Parser;

/**
 * Represents an event task with a specific time period.
 */
public class Event extends Task {
    private final String period;

    /**
     * Constructs an Event task with a description and a time period.
     *
     * @param description A brief description of the event.
     * @param start       The start time of the event in string format.
     * @param end         The end time of the event in string format.
     */
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
