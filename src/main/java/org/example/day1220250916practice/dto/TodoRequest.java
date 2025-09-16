package org.example.day1220250916practice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {
    @NotBlank(message = "Text cannot be empty")
    private String text;
    private boolean done;
}
