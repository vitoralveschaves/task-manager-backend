package com.hookfeat.todo.services;

import com.hookfeat.todo.entities.Todo;
import com.hookfeat.todo.entities.dtos.TodoRequestDto;
import com.hookfeat.todo.exceptions.IdNotFoundException;
import com.hookfeat.todo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    public void createTodoItem(TodoRequestDto todoRequestDto) {
        Todo newTodo = new Todo(todoRequestDto);
        this.todoRepository.save(newTodo);
    }

    public Todo getById(Integer id) {
        Optional<Todo> todoItem = this.todoRepository.findById(id);
        return todoItem.orElseThrow(() -> new IdNotFoundException("Identificador não encontrado"));
    }

    public Todo updateById(Todo todo) {
        Optional<Todo> todoItem = this.todoRepository.findById(todo.getId());
        if(todoItem.isEmpty()) {
            throw new IdNotFoundException();
        }
        var todoUpdate = todoItem.get();
        if(todo.getTitle() != null) {
            todoUpdate.setTitle(todo.getTitle());
        }
        if(todo.getTime() != null) {
            todoUpdate.setTime(todo.getTime());
        }
        if(todo.getDescription() != null) {
            todoUpdate.setDescription(todo.getDescription());
        }
        if(todo.getStatus() != null) {
            todoUpdate.setStatus(todo.getStatus());
        }
        Todo newTodo = new Todo(
                todoUpdate.getId(),
                todoUpdate.getTitle(),
                todoUpdate.getTime(),
                todoUpdate.getDescription(),
                todoUpdate.getStatus()
        );
        return this.todoRepository.save(newTodo);
    }

    public void deleteById(Integer id) {
        if(this.todoRepository.existsById(id)) {
            this.todoRepository.deleteById(id);
        }
    }
}
