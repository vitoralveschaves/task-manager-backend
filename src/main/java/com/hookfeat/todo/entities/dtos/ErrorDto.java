package com.hookfeat.todo.entities.dtos;

import org.springframework.http.HttpStatus;

public record ErrorDto(HttpStatus status, String message) {
}
