package com.example.todoapp.UserData;

import com.example.todoapp.WorkItem.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UserData> findByUsername(String username);

    UserData findUserByUsername(String username);

    @Query("SELECT w FROM UserData u LEFT JOIN u.workItemList w WHERE u.username=?1 ")
    List<WorkItem> getUsersTasks(String username);

    UserData findUserById(Long id);

    UserData findUserByUsernameAndEmail(String username, String email);
}
