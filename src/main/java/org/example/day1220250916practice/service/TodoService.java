package org.example.day1220250916practice.service;

import org.example.day1220250916practice.advice.InvalidTodoException;
import org.example.day1220250916practice.entity.Todo;
import org.example.day1220250916practice.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new InvalidTodoException("Todo not found"));
        existingTodo.setText(updatedTodo.getText());
        existingTodo.setDone(updatedTodo.isDone());

        return todoRepository.save(existingTodo);
    }



}
