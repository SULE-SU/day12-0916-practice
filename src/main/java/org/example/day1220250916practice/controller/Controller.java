package org.example.day1220250916practice.controller;

import org.example.day1220250916practice.entity.Todo;
import org.example.day1220250916practice.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

}
