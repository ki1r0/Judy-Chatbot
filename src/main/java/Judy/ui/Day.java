package Judy.ui;

/**
 * Represents different days in a week.
 */
public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public String getDayName() {
        return this.name();
    }
}

