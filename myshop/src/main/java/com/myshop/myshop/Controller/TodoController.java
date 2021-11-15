package com.myshop.myshop.Controller;

import com.myshop.myshop.Service.TodoService;
import com.myshop.myshop.entity.Todo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired
    TodoService TodoService;
    @ApiOperation("取得所有代辦事項列表")
    @ApiResponses({
            @ApiResponse(code=401,message="沒有權限"),
            @ApiResponse(code=404,message="找不到路徑")
    })
    @GetMapping("/Todos")
    public ResponseEntity getTodos() {
        Iterable<Todo> TodoList = TodoService.getTodos();
        return ResponseEntity.status(HttpStatus.OK).body(TodoList);
    }

    @GetMapping("/Todos/{id}")
    public Optional<Todo> getTodo(@PathVariable Integer id) {
        Optional<Todo> Todo = TodoService.findById(id);
        return Todo;
    }

    @PostMapping("/Todos/1")
    public ResponseEntity createTodo(@RequestBody Todo Todo) {
        Integer rlt = TodoService.createTodo(Todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(rlt);
    }

    @PutMapping("/Todos/{id}")
    public ResponseEntity upadteTodo(@PathVariable Integer id, @RequestBody Todo Todo) {
        Boolean rlt = TodoService.updateTodo(id ,Todo);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status 欄位不能為空");
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @DeleteMapping("/Todos/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id) {
        Boolean rlt = TodoService.deleteTodo(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}
