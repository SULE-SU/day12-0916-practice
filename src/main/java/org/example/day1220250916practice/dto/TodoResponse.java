package org.example.day1220250916practice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponse {
    private String id;
    private String text;
    private boolean done;
}
