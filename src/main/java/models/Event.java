package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent Event
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate from;
    private LocalDate to;

    /**
     * Initialize Event with name and isDone = false
     */
    public Event(String name, LocalDate from, LocalDate to) {
        setName(name);
        setIsDone(false);
        this.from = from;
        this.to = to;
    }

    /**
     * Initialize Event with name and isDone
     */
    @JsonCreator
    public Event(@JsonProperty("name") String name,
            @JsonProperty("isDone") boolean isDone,
            @JsonProperty("from") String from,
            @JsonProperty("to") String to) {
        setName(name);
        setIsDone(isDone);
        this.from = LocalDate.parse(from, DATE_FORMATTER);
        this.to = LocalDate.parse(to, DATE_FORMATTER);
    }

    /**
     * Initialize Event with name and isDone
     */
    public Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        setName(name);
        setIsDone(isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    /**
     * Custom getter for JSON serialization that returns due date as String
     */
    @JsonGetter("from")
    public String getFromAsString() {
        return from != null ? from.format(DATE_FORMATTER) : null;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    /**
     * Custom getter for JSON serialization that returns due date as String
     */
    @JsonGetter("to")
    public String getDueAsString() {
        return to != null ? to.format(DATE_FORMATTER) : null;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), from.format(DATE_FORMATTER), to.format(DATE_FORMATTER));
    }
}
