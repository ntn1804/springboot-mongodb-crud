package com.javatechie.service;

import com.javatechie.model.Task;
import com.javatechie.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Create
    public Task addTask(Task task) {
        task.setTaskId((UUID.randomUUID().toString().split("-")[0]));
        return taskRepository.save(task);
    }

    // Read
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskByTaskId(String taskId) {
        return taskRepository.findById(taskId).get();
    }

    public List<Task> getTasksBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }

    public List<Task> getTasksByAssignee(String assignee){
        return taskRepository.getTasksByAssignee(assignee);
    }

    // Update
    public Task updateTask(Task taskRequest) {

        // get existing task from db
        Task existingTask = taskRepository.findById(taskRequest.getTaskId()).get();

        // set new values to existing task
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());

        // save updated task to db
        return taskRepository.save(existingTask);
    }

    // Delete
    public String deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        return "Object with task ID " + taskId + " has been deleted successfully";
    }
}
