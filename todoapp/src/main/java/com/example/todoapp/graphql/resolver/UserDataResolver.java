package com.example.todoapp.graphql.resolver;


import com.example.todoapp.UserData.UserData;
import com.example.todoapp.UserData.UserRegistrationDto;
import com.example.todoapp.UserData.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final UserService userService;

    public UserDataResolver(UserService userService) {
        this.userService = userService;
    }

    public UserData getUserById(Long id) {
        return userService.getUserById(id);
    }

    public List<UserData> getAllUsers() {
        return userService.getAllUsers();
    }

    public UserData createUser(UserRegistrationDto input) {
        return userService.createUser(input);
    }

    public UserData updateUser(String name, UserRegistrationDto input) {
        return userService.updateUser(name, input);
    }

    public String deleteUser(String username) {
        userService.deleteUser(username);
        return username;
    }
}

