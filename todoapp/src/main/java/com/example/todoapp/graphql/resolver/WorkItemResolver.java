package com.example.todoapp.graphql.resolver;


import com.example.todoapp.WorkItem.WorkItem;
import com.example.todoapp.WorkItem.WorkItemCreateDto;
import com.example.todoapp.WorkItem.WorkItemService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkItemResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final WorkItemService workItemService;

    public WorkItemResolver(WorkItemService workItemService) {
        this.workItemService = workItemService;
    }

    public WorkItem getWorkItemById(Long id) {
        return workItemService.getTaskById(id);
    }

    public List<WorkItem> getAllWorkItems() {
        return workItemService.getTasks();
    }

    public WorkItem createWorkItem(WorkItemCreateDto input) {
        return workItemService.createTask(input);
    }

    public WorkItem updateWorkItem(String taskName, WorkItemCreateDto input) {
        return workItemService.updateTask(taskName, input);
    }

    public String deleteWorkItem(String taskName) {
        workItemService.deleteTask(taskName);
        return taskName;
    }
}
