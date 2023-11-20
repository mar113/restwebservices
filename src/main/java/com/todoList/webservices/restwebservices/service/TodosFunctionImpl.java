package com.todoList.webservices.restwebservices.service;

import com.todoList.webservices.restwebservices.Exceptions.ItemNotFoundException;
import com.todoList.webservices.restwebservices.model.Todo;
import com.todoList.webservices.restwebservices.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodosFunctionImpl implements TodosFunction{
    private static List<Todo> todos = new ArrayList<>();
    private static long counter = 0;
    static {
        todos.add(new Todo(++counter,"test1","todoFirstItem",new Date(),false));
        todos.add(new Todo(++counter,"test2","todoSecondItem",new Date(),false));
        todos.add(new Todo(++counter,"test3","todoThirdItem",new Date(),false));
        todos.add(new Todo(++counter,"test4","todoFourthItem",new Date(),false));
    }
    @Autowired
    private TodoRepository todoRepository;
    @Override
    public List<Todo> getAllTodos(String userName) {
        //return todoRepository.findAll();
        return todos;
    }

    @Override
    public Todo getTodo(long id) throws Exception {
        /*Optional<Todo> todoItem= todoRepository.findById(id);

        if(todoItem.isEmpty())
            throw new ItemNotFoundException("Item not found");
        return todoItem.get();*/
        Todo todo = findById(id);
        if(todo!=null) {return todo;}
        return null;
        }

    @Override
    public Todo addTodoItem(Todo todoItem) {
        if(todoItem.getId()<=-1){
            todoItem.setId(++counter);
            todos.add(todoItem);
        }
        return todoItem;

    }
    @Override
    public Todo updateTodoItem(Todo todoItem, long id) {
        deleteTodoItem(todoItem.getId());
        todos.add(todoItem);
        return todoItem;
    }

    @Override
    public Todo deleteTodoItem(long id)  {
        Todo todo = findById(id);
        if(todo==null) return null;
        if(todos.remove(todo)){
            return todo;
        }
        //todoRepository.deleteById(id);
        return null;
    }

    private Todo findById(long id) {

        for(Todo todo:todos){
            if(todo.getId() ==id){
                return todo;
            }
        }
        return null;
    }
}
