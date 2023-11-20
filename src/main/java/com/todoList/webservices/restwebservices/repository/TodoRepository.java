package com.todoList.webservices.restwebservices.repository;

import com.todoList.webservices.restwebservices.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
