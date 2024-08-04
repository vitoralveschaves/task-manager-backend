package com.hookfeat.todo.entities.dtos;

import com.hookfeat.todo.entities.enums.Time;

public record TodoRequestDto(String title, Time time, String description) {
}
