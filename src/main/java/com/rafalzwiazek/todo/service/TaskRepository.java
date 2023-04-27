package com.rafalzwiazek.todo.service;

import com.rafalzwiazek.todo.data.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
