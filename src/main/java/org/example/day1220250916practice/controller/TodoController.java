package org.example.day1220250916practice.controller;

import org.example.day1220250916practice.advice.InvalidTodoException;
import org.example.day1220250916practice.entity.Todo;
import org.example.day1220250916practice.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    List<Todo> index() {
        return todoService.index();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Todo createTodo(@Validated @RequestBody Todo todo) throws InvalidTodoException {
        if (todo.getText() == null || todo.getText().trim().isEmpty()) {
            throw new InvalidTodoException("Todo text must not be empty");
        } else {
            todo.setId(null);
            return todoService.createTodo(todo);
        }
    }



}
