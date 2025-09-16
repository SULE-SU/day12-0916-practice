package org.example.day1220250916practice;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class Controller {

    private final TodoRepository todoRepository;

    public Controller(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    List<Todo> index() {
        return todoRepository.findAll();
    }


}
