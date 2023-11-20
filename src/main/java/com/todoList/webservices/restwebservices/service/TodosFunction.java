package com.todoList.webservices.restwebservices.service;

import com.todoList.webservices.restwebservices.Exceptions.ItemNotFoundException;
import com.todoList.webservices.restwebservices.model.Todo;

import java.util.List;

public interface TodosFunction {

    List<Todo> getAllTodos(String userName);
    Todo getTodo(long id) throws Exception;
    Todo addTodoItem(Todo todoItem);
    Todo updateTodoItem(Todo todoItem,long id);
    Todo deleteTodoItem(long id);


}
