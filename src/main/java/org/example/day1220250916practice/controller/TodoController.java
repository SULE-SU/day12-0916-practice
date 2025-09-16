package org.example.day1220250916practice.controller;

import org.example.day1220250916practice.dto.TodoRequest;
import org.example.day1220250916practice.dto.TodoResponse;
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
    public List<TodoResponse> index() {
        return todoService.index();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@RequestBody @Validated TodoRequest todoRequest) {
        return todoService.createTodo(todoRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse updateTodo(@PathVariable String id, @RequestBody @Validated TodoRequest todoRequest) {
        return todoService.updateTodo(id, todoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }
}
