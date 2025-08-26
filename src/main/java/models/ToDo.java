package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent ToDo
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo with name and isDone = false
     */
    public ToDo(String name) {
        setName(name);
        setIsDone(false);
    }

    /**
     * Constructs a ToDo with name and isDone
     */
    @JsonCreator
    public ToDo(@JsonProperty("name") String name,
            @JsonProperty("isDone") boolean isDone) {
        setName(name);
        setIsDone(isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
