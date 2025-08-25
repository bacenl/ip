package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent Deadline
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate due;

    public Deadline() {
    }

    /**
     * Initialize Deadline with name and isDone = false
     */
    public Deadline(String name, LocalDate due) {
        setName(name);
        setIsDone(false);
        this.due = due;
    }

    /**
     * Initialize Deadline with name and isDone
     */
    public Deadline(String name, boolean isDone, LocalDate due) {
        setName(name);
        setIsDone(isDone);
        this.due = due;
    }

    /**
     * Initialize Deadline with name and isDone
     */
    @JsonCreator
    public Deadline(@JsonProperty("name") String name,
                   @JsonProperty("isDone") boolean isDone,
                   @JsonProperty("due") String due) {
        setName(name);
        setIsDone(isDone);
        this.due = LocalDate.parse(due, DATE_FORMATTER);
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    /**
     * Custom getter for JSON serialization that returns due date as String
     */
    @JsonGetter("due")
    public String getDueAsString() {
        return due != null ? due.format(DATE_FORMATTER) : null;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), due.format(DATE_FORMATTER));
    }
}
