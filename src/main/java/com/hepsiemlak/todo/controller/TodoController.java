package com.hepsiemlak.todo.controller;

import com.hepsiemlak.todo.model.Todo;
import com.hepsiemlak.todo.model.dto.TodoRequest;
import com.hepsiemlak.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-list")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{todoListId}/todo")
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAll(@PathVariable String todoListId){
        return todoService.getTodos(todoListId);
    }

    @PostMapping("/{todoListId}/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@PathVariable String todoListId, @RequestBody TodoRequest todoRequest){
        todoService.saveTodo(todoListId, todoRequest);
    }

    @DeleteMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        todoService.deleteTodoById(id);
    }

    @PutMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo update(@PathVariable String id, @RequestBody TodoRequest todoRequest){
        return todoService.updateTodo(id, todoRequest);
    }

    @PostMapping("/todo/{id}/check")
    @ResponseStatus(HttpStatus.OK)
    public void check(@PathVariable String id){
        todoService.checkTodo(id);
    }
}
