package com.todoList.webservices.restwebservices.controller;

import com.todoList.webservices.restwebservices.model.Todo;
import com.todoList.webservices.restwebservices.service.TodosFunctionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class TodoController {
    @Autowired
    private TodosFunctionImpl todosFunction;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        //return todosFunction.getAllTodos(username);
       /* List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1, "marwen", "todoFirstItem", new Date(), false));
        todos.add(new Todo(2, "marwen", "todoSecondItem", new Date(), false));
        todos.add(new Todo(3, "marwen", "todoThirdItem", new Date(), false));
        todos.add(new Todo(4, "marwen", "todoFourthItem", new Date(), false));*/
        return todosFunction.getAllTodos(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodoItem(@PathVariable String username,@PathVariable long id) throws Exception {
        return todosFunction.getTodo(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable String username, @PathVariable long id) {

        Todo todo = todosFunction.deleteTodoItem(id);
        //todosFunction.deleteTodoItem(id);
        if(todo != null){
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
        Todo todoItem = todosFunction.updateTodoItem(todo,id);
        return new ResponseEntity<Todo>(todoItem, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Todo> saveTodo(@PathVariable String username, @RequestBody Todo todo){
        Todo todoItem = todosFunction.addTodoItem(todo);

      URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoItem.getId()).toUri();

      return ResponseEntity.created(uri).build();
    }
}


