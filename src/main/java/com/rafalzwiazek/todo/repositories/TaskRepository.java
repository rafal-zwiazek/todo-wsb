package com.rafalzwiazek.todo.repositories;

import com.rafalzwiazek.todo.data.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
