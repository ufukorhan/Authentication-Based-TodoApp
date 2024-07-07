package com.hepsiemlak.todo.controller;

import com.hepsiemlak.todo.model.TodoList;
import com.hepsiemlak.todo.model.dto.TodoListRequest;
import com.hepsiemlak.todo.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-list")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoList> getAll(){
        return todoListService.getTodoLists();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoList get(@PathVariable String id){
        return todoListService.getTodoListById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody TodoListRequest todoListRequest){
        todoListService.saveTodoList(todoListRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoList update(@PathVariable String id, @RequestBody TodoListRequest todoListRequest){
        return todoListService.updateTodoList(id, todoListRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        todoListService.deleteTodoList(id);
    }
}
