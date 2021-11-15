package com.myshop.myshop.Service;


import com.myshop.myshop.dao.TodoDao;


import com.myshop.myshop.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TodoService {
    @Autowired
    TodoDao TodoDao;

    public Iterable<Todo> getTodos() {
        return TodoDao.findAll();
    }

    public Integer createTodo(Todo Todo) {
        Todo rltTodo = TodoDao.save(Todo);
        return rltTodo.getId();
    }

    public Boolean updateTodo(Integer id,Todo Todo) {
        Optional<Todo> isExistTodo = findById(id);
        if (! isExistTodo.isPresent()) {
            return false;
        }
        Todo newTodo = isExistTodo.get();
        if (Todo.getStatus() == null) {
            return false;
        }
        newTodo.setStatus(Todo.getStatus());
        TodoDao.save(newTodo);
        return true;
    }

    public Optional<Todo> findById(Integer id) {
        Optional<Todo> Todo = TodoDao.findById(id);
        return Todo;
    }

    public Boolean deleteTodo(Integer id) {
        Optional<Todo> findTodo = findById(id);
        if (!findTodo.isPresent()) {
            return false;
        }
        TodoDao.deleteById(id);
        return true;
    }
}