package org.example.day1220250916practice.repository;

import org.example.day1220250916practice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository  extends JpaRepository<Todo, String> {
}
