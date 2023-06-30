package com.example.todoapp.WorkItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {
    Optional<WorkItem> findTaskByTaskName(String taskName);

    void deleteByTaskName(String taskName);

    WorkItem findWorkItemById(Long taskId);

    @Query("SELECT w FROM WorkItem w WHERE w.isCompleted = true ")
    List<WorkItem> findCompletedTasks();


}
