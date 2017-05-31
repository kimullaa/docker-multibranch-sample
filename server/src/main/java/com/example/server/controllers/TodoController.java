package com.example.server.controllers;

import com.example.server.models.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("todos")
public class TodoController {
    @GetMapping
    public List<Todo> getAll() {
        return Arrays.asList(
                new Todo(1L, "xxx", false),
                new Todo(2L, "yyy", true),
                new Todo(3L, "zzz", false)
        );
    }
}
