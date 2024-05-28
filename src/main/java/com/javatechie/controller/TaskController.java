package com.javatechie.controller;

import com.javatechie.model.Task;
import com.javatechie.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTaskByTaskId(@PathVariable String taskId) {
        return taskService.getTaskByTaskId(taskId);
    }

    @GetMapping("/severity/{severity}")
    public List<Task> getTasksBySeverity(@PathVariable int severity) {
        return taskService.getTasksBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> getTasksByAssignee(@PathVariable String assignee){
        return taskService.getTasksByAssignee(assignee);
    }

    @PatchMapping
    public Task modifyTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId) {
        return taskService.deleteTask(taskId);
    }
}
