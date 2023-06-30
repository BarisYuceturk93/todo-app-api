package com.example.todoapp.WorkItem;


import com.example.todoapp.UserData.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class WorkItemService {

    private final WorkItemRepository workItemRepository;
    private final UserRepository userRepository;

    public WorkItemService(WorkItemRepository workItemRepository,
                           UserRepository userRepository) {
        this.workItemRepository = workItemRepository;
        this.userRepository = userRepository;
    }

    public List<WorkItem> getTasks() {
        return workItemRepository.findAll();
    }

    public WorkItem getTaskById(Long id) {
        return workItemRepository.findById(id).orElse(null);
    }

    public WorkItem createTask(WorkItemCreateDto taskDto) {
        String formatType = "dd.MM.yyyy";
        DateFormat formating = new SimpleDateFormat(formatType);
        WorkItem task = new WorkItem();
        task = WorkItemMapper.INSTANCE.fromTaskCreateDtoToTask(taskDto);
        task.setCreateDate(formating.format(System.currentTimeMillis()));
        task.setUpdateDate(formating.format(System.currentTimeMillis()));
        return workItemRepository.save(task);
    }

    public WorkItem updateTask(String taskName, WorkItemCreateDto dto) {

        String formatType = "dd.MM.yyyy";
        DateFormat formating = new SimpleDateFormat(formatType);

        WorkItem task = workItemRepository.findTaskByTaskName(taskName)
                .orElseThrow(() -> new EntityNotFoundException("Bu isimde bir görev bulunamadı" + taskName));

        boolean isUpdated = false;

        if (dto.getTaskName() != null && !dto.getTaskName().equals(task.getTaskName())) {
            task.setTaskName(dto.getTaskName());
            isUpdated = true;
        }
        if (dto.getTaskExplanation() != null && !dto.getTaskExplanation().equals(task.getTaskExplanation())) {
            task.setTaskExplanation(dto.getTaskExplanation());
            isUpdated = true;
        }

        if (isUpdated) {
            String date = formating.format(System.currentTimeMillis());
            task.setUpdateDate(date);
            workItemRepository.save(task);
        }

        return task;
    }

    public void deleteTask(String taskName) {
        workItemRepository.deleteByTaskName(taskName);
    }

    public WorkItem markTaskAsCompleted(Long id) {
        WorkItem task = workItemRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(true);
            return workItemRepository.save(task);
        }
        return null;
    }

    public List<WorkItem> getCompletedItems(){
        return workItemRepository.findCompletedTasks();
    }


    public void deleteWorkItem(Long id) {
    }
}
