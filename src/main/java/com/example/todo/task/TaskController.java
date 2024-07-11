package com.example.todo.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        return ResponseEntity.ok().body(taskService.getTasks());
    }

    @GetMapping(path="/incomplete")
    public ResponseEntity<List<Task>> getIncompleteTasks(){
        return ResponseEntity.ok().body(taskService.getIncompleteTasks());
    }

    @GetMapping(path="/completed")
    public ResponseEntity<List<Task>> getCompletedTasks(){
        return ResponseEntity.ok().body(taskService.getCompletedTasks());
    }

    @GetMapping(path="/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("tasksId") Long taskId){

        return ResponseEntity.ok().body(taskService.getTask(taskId));
    }

    @PostMapping(path="/create")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.addTask(task));
    }

    @DeleteMapping(path="/{taskId}/delete")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().body("Task deleted");
    }


}
