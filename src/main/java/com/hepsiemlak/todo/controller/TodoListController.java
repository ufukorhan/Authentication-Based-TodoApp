package com.hepsiemlak.todo.controller;

import com.hepsiemlak.todo.model.TodoList;
import com.hepsiemlak.todo.model.dto.TodoListRequest;
import com.hepsiemlak.todo.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-list")
public class TodoListController {
    // TODO: Return ResponseEntity Object with related status code

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public List<TodoList> getAll(){
        return todoListService.getTodoLists();
    }

    @GetMapping("/{id}")
    public TodoList get(@PathVariable String id){
        return todoListService.getTodoListById(id);
    }

    @PostMapping
    public void save(@RequestBody TodoListRequest todoListRequest){
        todoListService.saveTodoList(todoListRequest);
    }

    @PutMapping("/{id}")
    public TodoList update(@PathVariable String id, @RequestBody TodoListRequest todoListRequest){
        return todoListService.updateTodoList(id, todoListRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        todoListService.deleteTodoList(id);
    }
}
