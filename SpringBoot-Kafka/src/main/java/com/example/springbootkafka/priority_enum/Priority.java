package com.example.springbootkafka.priority_enum;

import com.example.springbootkafka.utils.SystemException;

public enum Priority {
    HIGH("high"), 
    MEDIUM("medium"), 
    LOW("low");

    private final String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Priority fromValue(String value) {
        for (Priority priority : Priority.values()) {
            if (priority.value.equalsIgnoreCase(value)) {
                return priority;
            }
        }
        throw SystemException.wrap("Unknown priority: " + value);
    }
}