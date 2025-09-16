package org.example.day1220250916practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository  extends JpaRepository<Todo, String> {
}
