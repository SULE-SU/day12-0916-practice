package org.example.day1220250916practice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Todo {
    @Id
    private String id;
    private String text;
    private boolean done;


    public Todo(String id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public Todo() {
    }

    @PrePersist
    public void ensureId(){
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
