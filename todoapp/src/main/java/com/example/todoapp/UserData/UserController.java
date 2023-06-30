package com.example.todoapp.UserData;

import com.example.todoapp.WorkItem.WorkItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserData> createUser(@RequestBody UserRegistrationDto registrationDto) {
        UserData createdUser = userService.createUser(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<UserData> updateUser(@PathVariable String userName, @RequestBody UserRegistrationDto userDto) {
        UserData updatedUser = userService.updateUser(userName, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserData> getUserByUserName(@PathVariable String userName) {
        UserData user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserData>> getAllUsers() {
        List<UserData> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/taketask")
    public String takeTaskOn(@RequestBody TaskTakeDto dto){
        return userService.takeTaskOn(dto.getId(), dto.getName());
    }

    @GetMapping("/{username}/gettasks")
    public ResponseEntity<List<WorkItem>> getUsersTasks(@PathVariable String username){
        return ResponseEntity.ok(userService.getUsersTasks(username));
    }
}
