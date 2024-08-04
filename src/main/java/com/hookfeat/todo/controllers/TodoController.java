package com.hookfeat.todo.controllers;

import com.hookfeat.todo.entities.Todo;
import com.hookfeat.todo.entities.dtos.ResponseStatus;
import com.hookfeat.todo.entities.dtos.TodoRequestDto;
import com.hookfeat.todo.exceptions.IdNullException;
import com.hookfeat.todo.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAll() {
        var list = this.todoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    public ResponseEntity<ResponseStatus> createTodoItem(@RequestBody TodoRequestDto todoRequestDto) {
        this.todoService.createTodoItem(todoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStatus("Item criado com sucesso!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable("id") Integer id) {
        var todo = this.todoService.getById(id);
        if (todo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @PatchMapping
    public ResponseEntity<Todo> updateById(@RequestBody Todo todo) {

        if (todo.getId() == null) {
            throw new IdNullException("O identificador não pode ser nulo");
        }
        var updateTodo = this.todoService.updateById(todo);
        return ResponseEntity.status(HttpStatus.OK).body(updateTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id ) {
        this.todoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
