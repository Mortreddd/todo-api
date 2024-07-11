package com.example.todo.task;

import com.example.todo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task getTask(Long taskId){
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public List<Task> getIncompleteTasks(){
        return taskRepository.findAll()
                .stream()
                .filter( task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public List<Task> getCompletedTasks(){
        return taskRepository.findAll()
                .stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }

    public Task addTask(Task task){
        log.info("Task created: " + task.getTitle());
        return taskRepository.save(task);
    }

    public Task updateTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId){
        taskRepository.delete(getTask(taskId));
    }
}
