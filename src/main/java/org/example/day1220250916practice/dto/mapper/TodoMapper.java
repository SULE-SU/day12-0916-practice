package org.example.day1220250916practice.dto.mapper;

import org.example.day1220250916practice.dto.TodoRequest;
import org.example.day1220250916practice.dto.TodoResponse;
import org.example.day1220250916practice.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper {

    public TodoResponse toResponse(Todo todo) {
        TodoResponse response = new TodoResponse();
        BeanUtils.copyProperties(todo, response);
        return response;
    }

    public List<TodoResponse> toResponse(List<Todo> todos) {
        return todos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Todo toEntity(TodoRequest request) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(request, todo);
        return todo;
    }
}
