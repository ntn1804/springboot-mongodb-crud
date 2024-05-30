package com.javatechie.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortField {
    ID("taskId"),
    SEVERITY("severity"),
    STORY_POINT("storyPoint");

    private final String taskFieldName;
}
