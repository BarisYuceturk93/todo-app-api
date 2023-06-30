package com.example.todoapp.UserData;


import com.example.todoapp.WorkItem.WorkItem;
import com.example.todoapp.WorkItem.WorkItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final WorkItemRepository taskRepository;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, WorkItemRepository taskRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskRepository = taskRepository;
    }

    public UserData createUser(UserRegistrationDto registrationDto){

        String username = registrationDto.getUsername();
        String email = registrationDto.getEmail();

        if(userRepository.existsByUsername(username)) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılmaktadır");
        }

        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("Bu e-posta adresi zaten kullanılmaktadır");
        }

        UserData user = new UserData();
        user = UserMapper.INSTANCE.fromUserRegisterationDtoToUser(registrationDto);

        String password = passwordEncoder.encode(registrationDto.getPassword());
        return userRepository.save(user);
    }

    public UserData updateUser(String userName, UserRegistrationDto dto) {

        UserData user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new EntityNotFoundException("Bu isimde bir kullanıcı bulunamadı" + userName));

        boolean isUpdated = false;

        if (dto.getName() != null && !dto.getName().equals(user.getName())) {
            user.setName(dto.getName());
            isUpdated = true;
        }
        if (dto.getSurname() != null && !dto.getSurname().equals(user.getSurname())) {
            user.setSurname(dto.getSurname());
            isUpdated = true;
        }
        if (dto.getPassword() != null && !dto.getPassword().equals(user.getPassword())) {
            user.setPassword(dto.getPassword());
            isUpdated = true;
        }
        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            user.setEmail(dto.getEmail());
            isUpdated = true;
        }

        if (isUpdated) {
            userRepository.save(user);
        }

        return user;
    }

    public void deleteUser(String userName) {
        UserData user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new EntityNotFoundException("Bu kullanıcı adına sahip bir kullanıcı bulunamadı: " + userName));

        userRepository.delete(user);
    }

    public UserData getUserByUserName(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new EntityNotFoundException("Bu kullanıcı adına sahip bir kullanıcı bulunamadı: " + userName));
    }

    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    public String takeTaskOn(Long taskId, String username){

        UserData user = userRepository.findUserByUsername(username);
        WorkItem task = taskRepository.findWorkItemById(taskId);

        user.getWorkItemList().add(task);
        task.getUserlist().add(user);
        taskRepository.save(task);
        userRepository.save(user);

        return "Şu görevi başarıyla üstünüze aldınız: " +task.getTaskName();
    }

    public List<WorkItem> getUsersTasks(String username) {
        return userRepository.getUsersTasks(username);
    }

    public UserData getUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
