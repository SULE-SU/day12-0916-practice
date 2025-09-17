package org.example.day1220250916practice.service;

import org.example.day1220250916practice.dto.TodoRequest;
import org.example.day1220250916practice.dto.TodoResponse;
import org.example.day1220250916practice.dto.mapper.TodoMapper;
import org.example.day1220250916practice.entity.Todo;
import org.example.day1220250916practice.repository.TodoRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Tool(name = "findAllTodo", description = "Find all todo")
    public List<TodoResponse> index() {
        return todoMapper.toResponse(todoRepository.findAll());
    }

    @Tool(name = "createTodo", description = "Create a todo")
    public TodoResponse createTodo(TodoRequest todoRequest) {
        Todo todo = todoMapper.toEntity(todoRequest);
        todo.setId(null);
        return todoMapper.toResponse(todoRepository.save(todo));
    }

    @Tool(name = "updateTodo", description = "Update a todo")
    public TodoResponse updateTodo(String id, TodoRequest todoRequest) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found with id: " + id);
        }
        Todo existingTodo = optionalTodo.get();
        existingTodo.setText(todoRequest.getText());
        existingTodo.setDone(todoRequest.isDone());

        return todoMapper.toResponse(todoRepository.save(existingTodo));
    }

    @Tool(name = "deleteTodo", description = "Delete a todo")
    public void deleteTodo(String id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}
