package com.rafalzwiazek.todo.service;

import com.rafalzwiazek.todo.data.Task;
import com.rafalzwiazek.todo.data.User;
import com.rafalzwiazek.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public void saveTaskWithTimeStamp(Task task) {
        task.setCreated(LocalDate.now());
        taskRepository.save(task);
    }
    public boolean deleteTaskById(Task task, User user){
        Optional<Task> taskOptional = taskRepository.findById(task.getId());
        if(taskOptional.isEmpty()){
            return false;
        } else
            if(taskOptional.get().getUser().getId() == user.getId())
            {
                taskRepository.delete(taskOptional.get());
            }
        return true;
    }
}