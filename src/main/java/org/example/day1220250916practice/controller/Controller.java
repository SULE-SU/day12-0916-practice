package org.example.day1220250916practice.controller;

import org.example.day1220250916practice.entity.Todo;
import org.example.day1220250916practice.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class Controller {

    private final TodoService todoService;

    public Controller(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    List<Todo> index() {
        return todoService.index();
    }
}
