package com.example.todoapp.graphql;

import com.example.todoapp.UserData.UserData;
import com.example.todoapp.UserData.UserRegistrationDto;
import com.example.todoapp.UserData.UserRepository;
import com.example.todoapp.UserData.UserService;
import com.example.todoapp.WorkItem.WorkItem;
import com.example.todoapp.WorkItem.WorkItemCreateDto;
import com.example.todoapp.WorkItem.WorkItemRepository;
import com.example.todoapp.WorkItem.WorkItemService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class GraphQLService<CreateUserInput, UpdateUserInput, WorkItemInput, WorkItemUpdateInput> implements GraphQLQueryResolver {
    private final UserService userService;
    private final WorkItemService workItemService;
    private final WorkItemRepository workItemRepository;
    private final UserRepository userRepository;

    public GraphQLService(UserService userService, WorkItemService workItemService,
                          WorkItemRepository workItemRepository,
                          UserRepository userRepository) {
        this.userService = userService;
        this.workItemService = workItemService;
        this.workItemRepository = workItemRepository;
        this.userRepository = userRepository;
    }

    public UserData createUser(CreateUserInput input) {
        return userService.createUser((UserRegistrationDto) input);
    }

    public UserData updateUser(String userName, UpdateUserInput input) {
        return userService.updateUser(userName, (UserRegistrationDto) input);
    }

    public void deleteUser(String userName) {
        userService.deleteUser(userName);
    }

    public UserData getUserByUserName(String userName) {
        return userService.getUserByUserName(userName);
    }

    public List<UserData> getAllUsers() {
        return userService.getAllUsers();
    }

    public String takeTaskOn(long id, String name) {
        return userService.takeTaskOn(id, name);
    }

    public List<WorkItem> getUsersTasks(String username) {
        return userService.getUsersTasks(username);
    }

    public List<WorkItem> getAllWorkItems() {
        return workItemRepository.findAll();
    }

    public WorkItem getWorkItemById(Long id) {
        return workItemRepository.findWorkItemById(id);
    }

    public UserData getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public WorkItem createWorkItem(WorkItemInput input) {
        return workItemService.createTask((WorkItemCreateDto) input);
    }

    public WorkItem updateWorkItem(String name, WorkItemUpdateInput input) {
        return workItemService.updateTask(name, (WorkItemCreateDto) input);
    }

    public void deleteWorkItem(Long id) {
        workItemRepository.deleteById(id);
    }

}

