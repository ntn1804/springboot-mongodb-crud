package com.javatechie.controller;

import com.javatechie.Enum.SortField;
import com.javatechie.model.Task;
import com.javatechie.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Task> getAllTasks(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "3") int limit,
                                  @RequestParam(defaultValue = "ID") SortField sortField,
                                  @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(offset, limit, sortDirection, sortField.getTaskFieldName());
        return taskService.findAllTasks(pageable);
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
    public List<Task> getTasksByAssignee(@PathVariable String assignee) {
        return taskService.getTasksByAssignee(assignee);
    }

    @PatchMapping("/{taskId}")
    public Task modifyTask(@PathVariable String taskId,
                           @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId) {
        return taskService.deleteTask(taskId);
    }
}
