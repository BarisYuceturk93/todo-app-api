package com.example.todoapp.WorkItem;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class WorkItemController {
    private final WorkItemService workItemService;

    public WorkItemController(WorkItemService workItemService) {
        this.workItemService = workItemService;
    }

    @GetMapping
    public List<WorkItem> getTasks() {
        return workItemService.getTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkItem> getTaskById(@PathVariable("id") Long id) {
        WorkItem task = workItemService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<WorkItem> createTask(@RequestBody WorkItemCreateDto taskDto) {
        WorkItem task = workItemService.createTask(taskDto);
        if (task != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{taskName}")
    public ResponseEntity<WorkItem> updateTask(
            @PathVariable("taskName") String taskName,
            @RequestBody WorkItemCreateDto taskUpdateDto) {
        WorkItem updatedTask = workItemService.updateTask(taskName, taskUpdateDto);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{taskName}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskName") String taskName) {
        workItemService.deleteTask(taskName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<WorkItem> markTaskAsCompleted(@PathVariable("id") Long id) {
        WorkItem completedTask = workItemService.markTaskAsCompleted(id);
        if (completedTask != null) {
            return ResponseEntity.ok(completedTask);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/completedtasks")
    public List<WorkItem> getCompletedTasks(){
        return workItemService.getCompletedItems();
    }

}
