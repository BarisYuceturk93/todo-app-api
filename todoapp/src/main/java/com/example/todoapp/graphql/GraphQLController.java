package com.example.todoapp.graphql;

import com.example.todoapp.UserData.UserData;
import com.example.todoapp.WorkItem.WorkItem;
import graphql.ExecutionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graphql")
public class GraphQLController<CreateUserInput, UpdateUserInput, WorkItemInput, WorkItemUpdateInput> {

    private final GraphQLService graphQLService;

    public GraphQLController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserData> createUser(@RequestBody CreateUserInput input) {
        UserData user = graphQLService.createUser(input);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{userName}")
    public ResponseEntity<UserData> updateUser(@PathVariable String userName, @RequestBody UpdateUserInput input) {
        UserData user = graphQLService.updateUser(userName, input);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        graphQLService.deleteUser(userName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<UserData> getUserByUsername(@PathVariable String userName) {
        UserData user = graphQLService.getUserByUsername(userName);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserData>> getAllUsers() {
        List<UserData> users = graphQLService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/workItem")
    public ResponseEntity<WorkItem> createWorkItem(@RequestBody WorkItemInput input) {
        WorkItem workItem = graphQLService.createWorkItem(input);
        return ResponseEntity.ok(workItem);
    }

    @PutMapping("/workItem/{name}")
    public ResponseEntity<WorkItem> updateWorkItem(@PathVariable String name, @RequestBody WorkItemUpdateInput input) {
        WorkItem workItem = graphQLService.updateWorkItem(name, input);
        return ResponseEntity.ok(workItem);
    }

    @DeleteMapping("/workItem/{id}")
    public ResponseEntity<Void> deleteWorkItem(@PathVariable Long id) {
        graphQLService.deleteWorkItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/workItem/{id}")
    public ResponseEntity<WorkItem> getWorkItemById(@PathVariable Long id) {
        WorkItem workItem = graphQLService.getWorkItemById(id);
        return ResponseEntity.ok(workItem);
    }

    @GetMapping("/workItems")
    public ResponseEntity<List<WorkItem>> getAllWorkItems() {
        List<WorkItem> workItems = graphQLService.getAllWorkItems();
        return ResponseEntity.ok(workItems);
    }


}

