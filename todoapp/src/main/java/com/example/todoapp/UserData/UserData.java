package com.example.todoapp.UserData;

import com.example.todoapp.WorkItem.WorkItem;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @ManyToMany
    private List<WorkItem> workItemList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WorkItem> getWorkItemList() {
        return workItemList;
    }

    public void setWorkItemList(List<WorkItem> taskList) {
        this.workItemList = taskList;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public UserData() {
    }

    public UserData(long id, String name, String surname, String username, String password, String email, Set<String> roles, List<WorkItem> workItemList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.workItemList = workItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id && name.equals(userData.name) && surname.equals(userData.surname) && username.equals(userData.username) && password.equals(userData.password) && email.equals(userData.email) && roles.equals(userData.roles) && workItemList.equals(userData.workItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, username, password, email, roles, workItemList);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", workItemList=" + workItemList +
                '}';
    }
}

