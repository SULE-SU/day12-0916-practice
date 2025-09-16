package org.example.day1220250916practice.service;

import org.example.day1220250916practice.entity.Todo;
import org.example.day1220250916practice.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> index() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String id, Todo updatedTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id);
        }
        Todo existingTodo = optionalTodo.get();
        existingTodo.setText(updatedTodo.getText());
        existingTodo.setDone(updatedTodo.isDone());

        return todoRepository.save(existingTodo);
    }


}
