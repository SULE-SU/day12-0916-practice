package org.example.day1220250916practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class Controller {

    @GetMapping
    List<Todo> index() {
        return List.of();
    }
}
